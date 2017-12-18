package com.laquysoft.cleangitapp.data.mapper

import com.laquysoft.cleangitapp.data.factory.UserFactory
import com.laquysoft.cleangitapp.data.mapper.UserMapper
import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.data.model.UserEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class UserMapperTest {

    private lateinit var userMapper: UserMapper

    @Before
    fun setUp() {
        userMapper = UserMapper()
    }

    @Test
    fun mapFromEntityMapsData() {
        val userEntity = UserFactory.makeUserEntity()
        val user = userMapper.mapFromEntity(userEntity)

        assertUserDataEquality(userEntity, user)
    }

    @Test
    fun mapToEntityMapsData() {
        val cachedUser = UserFactory.makeUser()
        val userEntity = userMapper.mapToEntity(cachedUser)

        assertUserDataEquality(userEntity, cachedUser)
    }

    private fun assertUserDataEquality(userEntity: UserEntity,
                                           user: User) {
        assertEquals(userEntity.id, user.id)
        assertEquals(userEntity.login, user.login)
        assertEquals(userEntity.avatarUrl, user.avatarUrl)
    }

}
