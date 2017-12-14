package com.laquysoft.cleangitapp.data.source

import com.laquysoft.cleangitapp.data.model.UserDetailEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import com.laquysoft.cleangitapp.data.model.UserEntity
import com.laquysoft.cleangitapp.data.repository.UserCache
import com.laquysoft.cleangitapp.data.repository.UserDataStore
import javax.inject.Inject

/**
 * Implementation of the [UserDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class UserCacheDataStore @Inject constructor(private val userCache: UserCache) :
        UserDataStore {


    override fun saveUser(mapToEntity: UserDetailEntity): Completable {
        return userCache.saveUser(mapToEntity)
                .doOnComplete {
                    userCache.setLastCacheTime(System.currentTimeMillis())
                }
    }


    override fun getUser(login: String?): Flowable<UserDetailEntity> {
        return userCache.getUser(login)
    }

    /**
     * Clear all Users from the cache
     */
    override fun clearUsers(): Completable {
        return userCache.clearUsers()
    }

    /**
     * Save a given [List] of [UserEntity] instances to the cache
     */
    override fun saveUsers(users: List<UserEntity>): Completable {
        return userCache.saveUsers(users)
                .doOnComplete {
                    userCache.setLastCacheTime(System.currentTimeMillis())
                }
    }

    /**
     * Retrieve a list of [UserEntity] instance from the cache
     */
    override fun getUsers(): Flowable<List<UserEntity>> {
        return userCache.getUsers()
    }

    /**
     * Retrieve a list of [UserEntity] instance from the cache
     */
    override fun isCached(): Single<Boolean> {
        return userCache.isCached()
    }

}
