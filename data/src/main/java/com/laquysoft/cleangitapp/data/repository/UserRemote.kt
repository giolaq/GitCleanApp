package com.laquysoft.cleangitapp.data.repository

import io.reactivex.Flowable
import com.laquysoft.cleangitapp.data.model.UserEntity

/**
 * Interface defining methods for the caching of Bufferroos. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface UserRemote {

    /**
     * Retrieve a list of Users, from the cache
     */
    fun getUsers(): Flowable<List<UserEntity>>

}
