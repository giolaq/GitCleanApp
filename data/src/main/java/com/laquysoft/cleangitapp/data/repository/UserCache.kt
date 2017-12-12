package com.laquysoft.cleangitapp.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import com.laquysoft.cleangitapp.data.model.UserEntity

/**
 * Interface defining methods for the caching of User. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface UserCache {

    /**
     * Clear all Users from the cache.
     */
    fun clearUsers(): Completable

    /**
     * Save a given list of Users to the cache.
     */
    fun saveUsers(users: List<UserEntity>): Completable

    fun saveUser(user: UserEntity): Completable

    /**
     * Retrieve a list of Users, from the cache.
     */
    fun getUsers(): Flowable<List<UserEntity>>


    /**
     * Retrieve an User, from the cache.
     */
    fun getUser(login: String?): Flowable<UserEntity>

    /**
     * Check whether there is a list of Users stored in the cache.
     *
     * @return true if the list is cached, otherwise false
     */
    fun isCached(): Single<Boolean>

    /**
     * Set a point in time at when the cache was last updated.
     *
     * @param lastCache the point in time at when the cache was last updated
     */
    fun setLastCacheTime(lastCache: Long)

    /**
     * Check if the cache is expired.
     *
     * @return true if the cache is expired, otherwise false
     */
    fun isExpired(): Boolean

}
