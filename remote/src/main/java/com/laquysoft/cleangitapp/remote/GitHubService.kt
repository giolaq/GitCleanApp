package com.laquysoft.cleangitapp.remote

import io.reactivex.Flowable
import com.laquysoft.cleangitapp.remote.model.UserModel
import retrofit2.http.GET

/**
 * Defines the abstract methods used for interacting with the GitHub API
 */
interface GitHubService {

    @GET(Endpoints.USERS)
    fun getUsers(): Flowable<List<UserModel>>


}
