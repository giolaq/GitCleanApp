package com.laquysoft.cleangitapp.ui

import com.laquysoft.cleangitapp.ui.mapper.UserMapper
import com.laquysoft.cleangitapp.ui.test.factory.UserFactory
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
    fun mapToViewMapsData() {
        val userView = UserFactory.makeUserView()
        val userViewModel = userMapper.mapToViewModel(userView)

        assertEquals(userView.login, userViewModel.login)
        assertEquals(userView.avatarUrl, userViewModel.avatarUrl)
    }

}
