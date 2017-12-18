package org.buffer.android.boilerplate.data.source

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import com.laquysoft.cleangitapp.data.repository.UserCache
import com.laquysoft.cleangitapp.data.source.UserCacheDataStore
import com.laquysoft.cleangitapp.data.source.UserDataStoreFactory
import com.laquysoft.cleangitapp.data.source.UserRemoteDataStore
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserDataStoreFactoryTest {

    private lateinit var userDataStoreFactory: UserDataStoreFactory

    private lateinit var userCache: UserCache
    private lateinit var userCacheDataStore: UserCacheDataStore
    private lateinit var userRemoteDataStore: UserRemoteDataStore

    @Before
    fun setUp() {
        userCache = mock()
        userCacheDataStore = mock()
        userRemoteDataStore = mock()
        userDataStoreFactory = UserDataStoreFactory(userCache,
                userCacheDataStore, userRemoteDataStore)
    }

    //<editor-fold desc="Retrieve Data Store">
    @Test
    fun retrieveDataStoreWhenNotCachedReturnsRemoteDataStore() {
        stubUserCacheIsCached(Single.just(false))
        val userDataStore = userDataStoreFactory.retrieveDataStore(false)
        assert(userDataStore is UserRemoteDataStore)
    }

    @Test
    fun retrieveDataStoreWhenCacheExpiredReturnsRemoteDataStore() {
        stubUserCacheIsCached(Single.just(true))
        stubUserCacheIsExpired(true)
        val userDataStore = userDataStoreFactory.retrieveDataStore(true)
        assert(userDataStore is UserRemoteDataStore)
    }

    @Test
    fun retrieveDataStoreReturnsCacheDataStore() {
        stubUserCacheIsCached(Single.just(true))
        stubUserCacheIsExpired(false)
        val userDataStore = userDataStoreFactory.retrieveDataStore(true)
        assert(userDataStore is UserCacheDataStore)
    }
    //</editor-fold>

    //<editor-fold desc="Retrieve Remote Data Store">
    @Test
    fun retrieveRemoteDataStoreReturnsRemoteDataStore() {
        val userDataStore = userDataStoreFactory.retrieveRemoteDataStore()
        assert(userDataStore is UserRemoteDataStore)
    }
    //</editor-fold>

    //<editor-fold desc="Retrieve Cache Data Store">
    @Test
    fun retrieveCacheDataStoreReturnsCacheDataStore() {
        val userDataStore = userDataStoreFactory.retrieveCacheDataStore()
        assert(userDataStore is UserCacheDataStore)
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubUserCacheIsCached(single: Single<Boolean>) {
        whenever(userCache.isCached())
                .thenReturn(single)
    }

    private fun stubUserCacheIsExpired(isExpired: Boolean) {
        whenever(userCache.isExpired())
                .thenReturn(isExpired)
    }
    //</editor-fold>

}
