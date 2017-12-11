package com.laquysoft.cleangitapp.presentation.test.factory

import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.presentation.model.UserView
import com.laquysoft.cleangitapp.presentation.test.factory.DataFactory.Factory.randomInt
import com.laquysoft.cleangitapp.presentation.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for User related instances
 */
class UserFactory {

    companion object Factory {

        fun makeUserList(count: Int): List<User> {
            val users = mutableListOf<User>()
            repeat(count) {
                users.add(makeUserModel())
            }
            return users
        }

        fun makeUserModel(): User {
            return User(randomInt(), randomUuid(), randomUuid())
        }

        fun makeUserViewList(count: Int): List<UserView> {
            val users = mutableListOf<UserView>()
            repeat(count) {
                users.add(makeUserView())
            }
            return users
        }

        fun makeUserView(): UserView {
            return UserView(randomUuid(), randomUuid())
        }

    }

}
