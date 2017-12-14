package com.laquysoft.cleangitapp.remote

import com.laquysoft.cleangitapp.remote.model.UserDetailModel
import io.reactivex.Flowable
import com.laquysoft.cleangitapp.remote.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Defines the abstract methods used for interacting with the GitHub API
 */
interface GitHubService {

    @GET(Endpoints.USERS)
    fun getUsers(): Flowable<List<UserModel>>

    @GET(Endpoints.USERS + "/{login}")
    fun getUser(@Path("login") login: String?): Flowable<UserDetailModel>


}
