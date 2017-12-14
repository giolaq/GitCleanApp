package com.laquysoft.cleangitapp.data.repository

import com.laquysoft.cleangitapp.data.model.UserDetailEntity
import io.reactivex.Flowable
import com.laquysoft.cleangitapp.data.model.UserEntity

/**
 * Interface defining methods for the caching of User. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface UserRemote {

    /**
     * Retrieve a list of Users, from the cache
     */
    fun getUsers(): Flowable<List<UserEntity>>
 /**
     * Retrieve an Users, from the cache
     */
    fun getUser(login: String?): Flowable<UserDetailEntity>

}
