package com.laquysoft.cleangitapp.data.source

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import com.laquysoft.cleangitapp.data.factory.UserFactory
import com.laquysoft.cleangitapp.data.model.UserEntity
import com.laquysoft.cleangitapp.data.repository.UserCache
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserCacheDataStoreTest {

    private lateinit var userCacheDataStore: UserCacheDataStore

    private lateinit var userCache: UserCache

    @Before
    fun setUp() {
        userCache = mock()
        userCacheDataStore = UserCacheDataStore(userCache)
    }

    //<editor-fold desc="Clear Users">
    @Test
    fun clearUsersCompletes() {
        stubUserCacheClearUsers(Completable.complete())
        val testObserver = userCacheDataStore.clearUsers().test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Save Users">
    @Test
    fun saveUsersCompletes() {
        stubUserCacheSaveUsers(Completable.complete())
        val testObserver = userCacheDataStore.saveUsers(
                UserFactory.makeUserEntityList(2)).test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Get Users">
    @Test
    fun getUsersCompletes() {
        stubUserCacheGetUsers(Flowable.just(UserFactory.makeUserEntityList(2)))
        val testObserver = userCacheDataStore.getUsers().test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubUserCacheSaveUsers(completable: Completable) {
        whenever(userCache.saveUsers(any()))
                .thenReturn(completable)
    }

    private fun stubUserCacheGetUsers(single: Flowable<List<UserEntity>>) {
        whenever(userCache.getUsers())
                .thenReturn(single)
    }

    private fun stubUserCacheClearUsers(completable: Completable) {
        whenever(userCache.clearUsers())
                .thenReturn(completable)
    }
    //</editor-fold>

}
