package com.laquysoft.cleangitapp.data

import io.reactivex.Completable
import io.reactivex.Flowable
import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.domain.repository.UserRepository
import com.laquysoft.cleangitapp.data.mapper.UserMapper
import com.laquysoft.cleangitapp.data.model.UserEntity
import com.laquysoft.cleangitapp.data.source.UserDataStoreFactory
import javax.inject.Inject

/**
 * Provides an implementation of the [UserRepository] interface for communicating to and from
 * data sources
 */
class UserDataRepository @Inject constructor(private val factory: UserDataStoreFactory,
                                             private val userMapper: UserMapper):
        UserRepository {

    override fun clearUsers(): Completable {
        return factory.retrieveCacheDataStore().clearUsers()
    }

    override fun saveUsers(users: List<User>): Completable {
        val userEntities = mutableListOf<UserEntity>()
        users.map { userEntities.add(userMapper.mapToEntity(it)) }
        return factory.retrieveCacheDataStore().saveUsers(userEntities)
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
