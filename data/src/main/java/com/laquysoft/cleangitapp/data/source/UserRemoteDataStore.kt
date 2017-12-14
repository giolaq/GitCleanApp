package com.laquysoft.cleangitapp.data.source

import com.laquysoft.cleangitapp.data.model.UserDetailEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import com.laquysoft.cleangitapp.data.model.UserEntity
import com.laquysoft.cleangitapp.data.repository.UserDataStore
import com.laquysoft.cleangitapp.data.repository.UserRemote
import javax.inject.Inject

/**
 * Implementation of the [UserDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class UserRemoteDataStore @Inject constructor(private val userRemote: UserRemote) :
        UserDataStore {


    override fun saveUser(mapToEntity: UserDetailEntity): Completable {
        throw UnsupportedOperationException()
    }
    override fun getUser(login: String?): Flowable<UserDetailEntity> {
        return userRemote.getUser(login)
    }

    override fun clearUsers(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveUsers(users: List<UserEntity>): Completable {
        throw UnsupportedOperationException()
    }

    /**
     * Retrieve a list of [UserEntity] instances from the API
     */
    override fun getUsers(): Flowable<List<UserEntity>> {
        return userRemote.getUsers()
    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }

}
