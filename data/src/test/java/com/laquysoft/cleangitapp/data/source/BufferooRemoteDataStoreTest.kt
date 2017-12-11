package org.buffer.android.boilerplate.data.source

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import com.laquysoft.cleangitapp.data.factory.UserFactory
import com.laquysoft.cleangitapp.data.model.UserEntity
import com.laquysoft.cleangitapp.data.repository.UserRemote
import com.laquysoft.cleangitapp.data.source.UserRemoteDataStore
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserRemoteDataStoreTest {

    private lateinit var userRemoteDataStore: UserRemoteDataStore

    private lateinit var userRemote: UserRemote

    @Before
    fun setUp() {
        userRemote = mock()
        userRemoteDataStore = UserRemoteDataStore(userRemote)
    }

    //<editor-fold desc="Clear Users">
    @Test(expected = UnsupportedOperationException::class)
    fun clearUsersThrowsException() {
        userRemoteDataStore.clearUsers().test()
    }
    //</editor-fold>

    //<editor-fold desc="Save Users">
    @Test(expected = UnsupportedOperationException::class)
    fun saveUsersThrowsException() {
        userRemoteDataStore.saveUsers(UserFactory.makeUserEntityList(2)).test()
    }
    //</editor-fold>

    //<editor-fold desc="Get Users">
    @Test
    fun getUsersCompletes() {
        stubUserCacheGetUsers(Flowable.just(UserFactory.makeBufferooEntityList(2)))
        val testObserver = userRemote.getUsers().test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubUserCacheGetUsers(single: Flowable<List<UserEntity>>) {
        whenever(userRemote.getUsers())
                .thenReturn(single)
    }
    //</editor-fold>

}
