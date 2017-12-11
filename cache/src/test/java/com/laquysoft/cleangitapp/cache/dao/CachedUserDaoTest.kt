package com.laquysoft.cleangitapp.cache.dao

import android.arch.persistence.room.Room
import com.laquysoft.cleangitapp.cache.db.UsersDatabase
import com.laquysoft.cleangitapp.cache.test.factory.UserFactory
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
open class CachedUserDaoTest {

    private lateinit var usersDatabase: UsersDatabase

    @Before
    fun initDb() {
        usersDatabase = Room.inMemoryDatabaseBuilder(
                RuntimeEnvironment.application.baseContext,
                UsersDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDb() {
        usersDatabase.close()
    }

    @Test
    fun insertUsersSavesData() {
        val cachedUsers = UserFactory.makeCachedUser()
        usersDatabase.cachedUserDao().insertUser(cachedUsers)

        val users = usersDatabase.cachedUserDao().getUsers()
        assert(users.isNotEmpty())
    }

    @Test
    fun getUsersRetrievesData() {
        val cachedUsers = UserFactory.makeCachedUsersList(5)

        cachedUsers.forEach {
            usersDatabase.cachedUserDao().insertUser(it) }

        val retrieveUsers = usersDatabase.cachedUserDao().getUsers()
        assert(retrieveUsers == cachedUsers.sortedWith(compareBy({ it.id }, { it.id })))
    }

}
