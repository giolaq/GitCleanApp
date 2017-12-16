package com.laquysoft.cleangitapp.data.repository

import com.laquysoft.cleangitapp.data.model.UserDetailEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import com.laquysoft.cleangitapp.data.model.UserEntity

/**
 * Interface defining methods for the data operations related to User.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface UserDataStore {

    fun clearUsers(): Completable

    fun saveUsers(users: List<UserEntity>): Completable

    fun getUsers(): Flowable<List<UserEntity>>

    fun getSearchUsers(q: String?): Flowable<List<UserEntity>>

    fun getUser(login: String?): Flowable<UserDetailEntity>

    fun isCached(): Single<Boolean>

    fun saveUser(mapToEntity: UserDetailEntity): Completable

}
