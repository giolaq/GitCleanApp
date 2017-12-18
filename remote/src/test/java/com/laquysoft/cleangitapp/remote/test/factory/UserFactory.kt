package com.laquysoft.cleangitapp.remote.test.factory

import com.laquysoft.cleangitapp.remote.GitHubService
import com.laquysoft.cleangitapp.remote.model.UserModel
import com.laquysoft.cleangitapp.remote.test.factory.DataFactory.Factory.randomInt
import com.laquysoft.cleangitapp.remote.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for User related instances
 */
class UserFactory {

    companion object Factory {

        fun makeUserResponse(): List<UserModel> {
            return makeUserModelList(5)
        }

        fun makeUserModelList(count: Int): List<UserModel> {
            val userEntities = mutableListOf<UserModel>()
            repeat(count) {
                userEntities.add(makeUserModel())
            }
            return userEntities
        }

        fun makeUserModel(): UserModel {
            return UserModel(randomInt(), randomUuid(), randomUuid())
        }

    }

}
