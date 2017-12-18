package com.laquysoft.cleangitapp.data

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import com.laquysoft.cleangitapp.data.factory.UserFactory
import com.laquysoft.cleangitapp.data.mapper.UserDetailMapper
import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.data.mapper.UserMapper
import com.laquysoft.cleangitapp.data.model.UserEntity
import com.laquysoft.cleangitapp.data.repository.UserDataStore
import com.laquysoft.cleangitapp.data.source.UserCacheDataStore
import com.laquysoft.cleangitapp.data.source.UserDataStoreFactory
import com.laquysoft.cleangitapp.data.source.UserRemoteDataStore
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserDataRepositoryTest {

    private lateinit var userDataRepository: UserDataRepository

    private lateinit var userDataStoreFactory: UserDataStoreFactory
    private lateinit var userMapper: UserMapper
    private lateinit var userDetailMapper: UserDetailMapper
    private lateinit var userCacheDataStore: UserCacheDataStore
    private lateinit var userRemoteDataStore: UserRemoteDataStore

    @Before
    fun setUp() {
        userDataStoreFactory = mock()
        userMapper = mock()
        userCacheDataStore = mock()
        userRemoteDataStore = mock()
        userDataRepository = UserDataRepository(userDataStoreFactory, userMapper, userDetailMapper)
        stubUserDataStoreFactoryRetrieveCacheDataStore()
        stubUserDataStoreFactoryRetrieveRemoteDataStore()
    }

    //<editor-fold desc="Clear Users">
    @Test
    fun clearUsersCompletes() {
        stubUserCacheClearUsers(Completable.complete())
        val testObserver = userDataRepository.clearUsers().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearUsersCallsCacheDataStore() {
        stubUserCacheClearUsers(Completable.complete())
        userDataRepository.clearUsers().test()
        verify(userCacheDataStore).clearUsers()
    }

    @Test
    fun clearUsersNeverCallsRemoteDataStore() {
        stubUserCacheClearUsers(Completable.complete())
        userDataRepository.clearUsers().test()
        verify(userRemoteDataStore, never()).clearUsers()
    }
    //</editor-fold>

    //<editor-fold desc="Save Users">
    @Test
    fun saveUsersCompletes() {
        stubUserCacheSaveUsers(Completable.complete())
        val testObserver = userDataRepository.saveUsers(
                UserFactory.makeUserList(2)).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveUsersCallsCacheDataStore() {
        stubUserCacheSaveUsers(Completable.complete())
        userDataRepository.saveUsers(UserFactory.makeUserList(2)).test()
        verify(userCacheDataStore).saveUsers(any())
    }

    @Test
    fun saveUsersNeverCallsRemoteDataStore() {
        stubUserCacheSaveUsers(Completable.complete())
        userDataRepository.saveUsers(UserFactory.makeUserList(2)).test()
        verify(userRemoteDataStore, never()).saveUsers(any())
    }
    //</editor-fold>

    //<editor-fold desc="Get Users">
    @Test
    fun getUsersCompletes() {
        stubUserCacheDataStoreIsCached(Single.just(true))
        stubUserDataStoreFactoryRetrieveDataStore(userCacheDataStore)
        stubUserCacheDataStoreGetUsers(Flowable.just(
                UserFactory.makeUserEntityList(2)))
        stubUserCacheSaveUsers(Completable.complete())
        val testObserver = userDataRepository.getUsers().test()
        testObserver.assertComplete()
    }

    @Test
    fun getUsersReturnsData() {
        stubUserCacheDataStoreIsCached(Single.just(true))
        stubUserDataStoreFactoryRetrieveDataStore(userCacheDataStore)
        stubUserCacheSaveUsers(Completable.complete())
        val users = UserFactory.makeUserList(2)
        val userEntities = UserFactory.makeUserEntityList(2)
        users.forEachIndexed { index, user ->
            stubUserMapperMapFromEntity(userEntities[index], user) }
        stubUserCacheDataStoreGetUsers(Flowable.just(userEntities))

        val testObserver = userDataRepository.getUsers().test()
        testObserver.assertValue(users)
    }

    @Test
    fun getUsersSavesUsersWhenFromCacheDataStore() {
        stubUserDataStoreFactoryRetrieveDataStore(userCacheDataStore)
        stubUserCacheSaveUsers(Completable.complete())
        userDataRepository.saveUsers(UserFactory.makeUserList(2)).test()
        verify(userCacheDataStore).saveUsers(any())
    }

    @Test
    fun getUsersNeverSavesUsersWhenFromRemoteDataStore() {
        stubUserDataStoreFactoryRetrieveDataStore(userRemoteDataStore)
        stubUserCacheSaveUsers(Completable.complete())
        userDataRepository.saveUsers(UserFactory.makeUserList(2)).test()
        verify(userRemoteDataStore, never()).saveUsers(any())
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubUserCacheSaveUsers(completable: Completable) {
        whenever(userCacheDataStore.saveUsers(any()))
                .thenReturn(completable)
    }

    private fun stubUserCacheDataStoreIsCached(single: Single<Boolean>) {
        whenever(userCacheDataStore.isCached())
                .thenReturn(single)
    }

    private fun stubUserCacheDataStoreGetUsers(single: Flowable<List<UserEntity>>) {
        whenever(userCacheDataStore.getUsers())
                .thenReturn(single)
    }

    private fun stubUserRemoteDataStoreGetUsers(single: Flowable<List<UserEntity>>) {
        whenever(userRemoteDataStore.getUsers())
                .thenReturn(single)
    }

    private fun stubUserCacheClearUsers(completable: Completable) {
        whenever(userCacheDataStore.clearUsers())
                .thenReturn(completable)
    }

    private fun stubUserDataStoreFactoryRetrieveCacheDataStore() {
        whenever(userDataStoreFactory.retrieveCacheDataStore())
                .thenReturn(userCacheDataStore)
    }

    private fun stubUserDataStoreFactoryRetrieveRemoteDataStore() {
        whenever(userDataStoreFactory.retrieveRemoteDataStore())
                .thenReturn(userCacheDataStore)
    }

    private fun stubUserDataStoreFactoryRetrieveDataStore(dataStore: UserDataStore) {
        whenever(userDataStoreFactory.retrieveDataStore(any()))
                .thenReturn(dataStore)
    }

    private fun stubUserMapperMapFromEntity(userEntity: UserEntity,
                                                user: User) {
        whenever(userMapper.mapFromEntity(userEntity))
                .thenReturn(user)
    }
    //</editor-fold>

}
