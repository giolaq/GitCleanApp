package com.laquysoft.cleangitapp.remote

import com.laquysoft.cleangitapp.remote.mapper.UserEntityMapper
import com.laquysoft.cleangitapp.remote.model.UserModel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import com.laquysoft.cleangitapp.remote.test.factory.UserFactory
import com.laquysoft.cleangitapp.data.model.UserEntity
import com.laquysoft.cleangitapp.remote.mapper.UserDetailEntityMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserRemoteImplTest {

    private lateinit var entityMapper: UserEntityMapper
    private lateinit var entityDetailMapper: UserDetailEntityMapper
    private lateinit var userService: GitHubService

    private lateinit var userRemoteImpl: GitHubRemoteImpl

    @Before
    fun setup() {
        entityMapper = mock()
        userService = mock()
        userRemoteImpl = GitHubRemoteImpl(userService, entityMapper, entityDetailMapper)
    }

    //<editor-fold desc="Get Users">
    @Test
    fun getUsersCompletes() {
        stubUserServiceGetUsers(Flowable.just(UserFactory.makeUserResponse()))
        val testObserver = userRemoteImpl.getUsers().test()
        testObserver.assertComplete()
    }

    @Test
    fun getUsersReturnsData() {
        val userResponse = UserFactory.makeUserResponse()
        stubUserServiceGetUsers(Flowable.just(userResponse))
        val userEntities = mutableListOf<UserEntity>()
        userResponse.forEach {
            userEntities.add(entityMapper.mapFromRemote(it))
        }

        val testObserver = userRemoteImpl.getUsers().test()
        testObserver.assertValue(userEntities)
    }
    //</editor-fold>

    private fun stubUserServiceGetUsers(observable:
                                                Flowable<List<UserModel>>) {
        whenever(userService.getUsers())
                .thenReturn(observable)
    }
}
