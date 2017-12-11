package com.laquysoft.cleangitapp.cache.test.factory

import com.laquysoft.cleangitapp.cache.model.CachedUser
import com.laquysoft.cleangitapp.cache.test.factory.DataFactory.Factory.randomInt
import com.laquysoft.cleangitapp.cache.test.factory.DataFactory.Factory.randomUuid
import com.laquysoft.cleangitapp.data.model.UserEntity

/**
 * Factory class for User related instances
 */
class UserFactory {

    companion object Factory {

        fun makeCachedUser(): CachedUser {
            return CachedUser(randomInt(), randomUuid(), randomUuid())
        }

        fun makeUserEntity(): UserEntity {
            return UserEntity(randomInt(), randomUuid(), randomUuid())
        }

        fun makeUserEntityList(count: Int): List<UserEntity> {
            val userEntities = mutableListOf<UserEntity>()
            repeat(count) {
                userEntities.add(makeUserEntity())
            }
            return userEntities
        }

        fun makeCachedUsersList(count: Int): List<CachedUser> {
            val cachedUsers = mutableListOf<CachedUser>()
            repeat(count) {
                cachedUsers.add(makeCachedUser())
            }
            return cachedUsers
        }

    }

}
