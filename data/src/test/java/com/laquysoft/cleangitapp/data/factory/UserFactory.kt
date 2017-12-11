package com.laquysoft.cleangitapp.data.factory

import com.laquysoft.cleangitapp.data.model.UserEntity
import com.laquysoft.cleangitapp.data.factory.DataFactory.Factory.randomUuid
import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.data.factory.DataFactory.Factory.randomInt

/**
 * Factory class for User related instances
 */
class UserFactory {

    companion object Factory {

        fun makeUserEntity(): UserEntity {
            return UserEntity(randomInt(), randomUuid(), randomUuid())
        }

        fun makeUser(): User {
            return User(randomInt(), randomUuid(), randomUuid())
        }

        fun makeUserEntityList(count: Int): List<UserEntity> {
            val userEntities = mutableListOf<UserEntity>()
            repeat(count) {
                userEntities.add(makeUserEntity())
            }
            return userEntities
        }

        fun makeUserList(count: Int): List<User> {
            val users = mutableListOf<User>()
            repeat(count) {
                users.add(makeUser())
            }
            return users
        }

    }

}
