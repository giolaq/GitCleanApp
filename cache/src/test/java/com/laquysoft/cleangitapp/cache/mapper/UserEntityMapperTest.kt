package com.laquysoft.cleangitapp.cache.mapper

import com.laquysoft.cleangitapp.cache.model.CachedUser
import com.laquysoft.cleangitapp.cache.test.factory.UserFactory
import com.laquysoft.cleangitapp.data.model.UserEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class UserEntityMapperTest {

    private lateinit var userEntityMapper: UserEntityMapper

    @Before
    fun setUp() {
        userEntityMapper = UserEntityMapper()
    }

    @Test
    fun mapToCachedMapsData() {
        val userEntity = UserFactory.makeUserEntity()
        val cachedUser = userEntityMapper.mapToCached(userEntity)

        assertUserDataEquality(userEntity, cachedUser)
    }

    @Test
    fun mapFromCachedMapsData() {
        val cachedUser = UserFactory.makeCachedUser()
        val userEntity = userEntityMapper.mapFromCached(cachedUser)

        assertUserDataEquality(userEntity, cachedUser)
    }

    private fun assertUserDataEquality(userEntity: UserEntity,
                                       cachedUser: CachedUser) {
        assertEquals(userEntity.login, cachedUser.login)
        assertEquals(userEntity.id, cachedUser.id)
        assertEquals(userEntity.avatarUrl, cachedUser.avatarUrl)
    }

}
