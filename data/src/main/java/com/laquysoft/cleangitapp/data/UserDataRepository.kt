package com.laquysoft.cleangitapp.data

import com.laquysoft.cleangitapp.data.mapper.UserDetailMapper
import com.laquysoft.cleangitapp.data.mapper.UserMapper
import com.laquysoft.cleangitapp.data.model.UserEntity
import com.laquysoft.cleangitapp.data.source.UserDataStoreFactory
import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.domain.model.UserDetail
import com.laquysoft.cleangitapp.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Provides an implementation of the [UserRepository] interface for communicating to and from
 * data sources
 */
class UserDataRepository @Inject constructor(private val factory: UserDataStoreFactory,
                                             private val userMapper: UserMapper,
                                             private val userDetailMapper: UserDetailMapper) :
        UserRepository {

    override fun getUser(login: String?): Flowable<UserDetail> {
        return factory.retrieveCacheDataStore().isCached()
                .flatMapPublisher {
                    factory.retrieveDataStore(it).getUser(login)
                }
                .flatMap {
                    Flowable.just(userDetailMapper.mapFromEntity(it))
                }
                .flatMap {
                    saveUser(it).toSingle { it }.toFlowable()
                }
    }

    override fun clearUsers(): Completable {
        return factory.retrieveCacheDataStore().clearUsers()
    }

    override fun saveUsers(users: List<User>): Completable {
        val userEntities = mutableListOf<UserEntity>()
        users.map { userEntities.add(userMapper.mapToEntity(it)) }
        return factory.retrieveCacheDataStore().saveUsers(userEntities)
    }

    override fun saveUser(user: UserDetail): Completable {
        return factory.retrieveCacheDataStore().saveUser(userDetailMapper.mapToEntity(user))
    }

    override fun getUsers(): Flowable<List<User>> {
        return factory.retrieveCacheDataStore().isCached()
                .flatMapPublisher {
                    factory.retrieveDataStore(it).getUsers()
                }
                .flatMap {
                    Flowable.just(it.map { userMapper.mapFromEntity(it) })
                }
                .flatMap {
                    saveUsers(it).toSingle { it }.toFlowable()
                }
    }

}
