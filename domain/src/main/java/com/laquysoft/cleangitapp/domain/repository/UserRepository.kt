package com.laquysoft.cleangitapp.domain.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.domain.model.UserDetail

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface UserRepository {

    fun clearUsers(): Completable

    fun saveUsers(users: List<User>): Completable

    fun saveUser(user: UserDetail): Completable

    fun getUsers(): Flowable<List<User>>

    fun getSearchUsers(q: String?): Flowable<List<User>>

    fun getUser(login: String?): Flowable<UserDetail>

}
