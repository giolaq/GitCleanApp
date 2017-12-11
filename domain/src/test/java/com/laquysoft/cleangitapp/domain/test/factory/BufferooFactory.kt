package com.laquysoft.cleangitapp.domain.test.factory

import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.domain.test.factory.DataFactory.Factory.randomInt
import com.laquysoft.cleangitapp.domain.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for User related instances
 */
class UserFactory {

    companion object Factory {

        fun makeUserList(count: Int): List<User> {
            val users = mutableListOf<User>()
            repeat(count) {
                users.add(makeUser())
            }
            return users
        }

        fun makeUser(): User {
            return User(randomInt(), randomUuid(), randomUuid())
        }

    }

}
