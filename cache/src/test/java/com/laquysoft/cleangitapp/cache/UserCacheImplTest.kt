package com.laquysoft.cleangitapp.cache

import android.arch.persistence.room.Room
import com.laquysoft.cleangitapp.cache.db.UsersDatabase
import com.laquysoft.cleangitapp.cache.mapper.UserDetailEntityMapper
import com.laquysoft.cleangitapp.cache.mapper.UserEntityMapper
import com.laquysoft.cleangitapp.cache.model.CachedUser
import com.laquysoft.cleangitapp.cache.test.factory.UserFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21))
class UserCacheImplTest {

    private var usersDatabase = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application,
            UsersDatabase::class.java).allowMainThreadQueries().build()
    private var entityMapper = UserEntityMapper()
    private var entityDetailMapper = UserDetailEntityMapper()
    private var preferencesHelper = PreferencesHelper(RuntimeEnvironment.application)


    private val databaseHelper = UserCacheImpl(usersDatabase,
            entityMapper, entityDetailMapper, preferencesHelper)

    @Test
    fun clearTablesCompletes() {
        val testObserver = databaseHelper.clearUsers().test()
        testObserver.assertComplete()
    }

    //<editor-fold desc="Save Users">
    @Test
    fun saveUsersCompletes() {
        val userEntities = UserFactory.makeUserEntityList(2)

        val testObserver = databaseHelper.saveUsers(userEntities).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveUsersSavesData() {
        val userCount = 2
        val userEntities = UserFactory.makeUserEntityList(userCount)

        databaseHelper.saveUsers(userEntities).test()
        checkNumRowsInUsersTable(userCount)
    }
    //</editor-fold>

    //<editor-fold desc="Get Users">
    @Test
    fun getUsersCompletes() {
        val testObserver = databaseHelper.getUsers().test()
        testObserver.assertComplete()
    }

    @Test
    fun getUsersReturnsData() {
        val userEntities = UserFactory.makeUserEntityList(2)
        val cachedUsers = mutableListOf<CachedUser>()
        userEntities.forEach {
            cachedUsers.add(entityMapper.mapToCached(it))
        }
        insertUsers(cachedUsers)

        val testObserver = databaseHelper.getUsers().test()
    }
    //</editor-fold>

    private fun insertUsers(cachedUsers: List<CachedUser>) {
        cachedUsers.forEach {
            usersDatabase.cachedUserDao().insertUser(it)
        }
    }

    private fun checkNumRowsInUsersTable(expectedRows: Int) {
        val numberOfRows = usersDatabase.cachedUserDao().getUsers().size
        assertEquals(expectedRows, numberOfRows)
    }

}
