package com.laquysoft.cleangitapp.ui.test.util

import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.presentation.model.UserView


/**
 * Factory class for User related instances
 */
object UserFactory {

    fun makeUserView(): UserView {
        return UserView(DataFactory.randomUuid(), DataFactory.randomUuid())
    }

    fun makeUserList(count: Int): List<User> {
        val users = mutableListOf<User>()
        repeat(count) {
            users.add(makeUserModel())
        }
        return users
    }

    fun makeUserModel(): User {
        return User(DataFactory.randomInt(), DataFactory.randomUuid(),
                DataFactory.randomUuid())
    }

}
