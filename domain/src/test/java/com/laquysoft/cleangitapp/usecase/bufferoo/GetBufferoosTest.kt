package com.laquysoft.cleangitapp.usecase.user

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import com.laquysoft.cleangitapp.domain.executor.PostExecutionThread
import com.laquysoft.cleangitapp.domain.executor.ThreadExecutor
import com.laquysoft.cleangitapp.domain.interactor.browse.GetUsers
import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.domain.repository.UserRepository
import com.laquysoft.cleangitapp.domain.test.factory.UserFactory
import org.junit.Before
import org.junit.Test

class GetUsersTest {

    private lateinit var getUsers: GetUsers

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockUserRepository: UserRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockUserRepository = mock()
        getUsers = GetUsers(mockUserRepository, mockThreadExecutor,
                mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getUsers.buildUseCaseObservable(null)
        verify(mockUserRepository).getUsers()
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubUserRepositoryGetUsers(Flowable.just(BufferooFactory.makeBufferooList(2)))
        val testObserver = getUsers.buildUseCaseObservable(null).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val users = UserFactory.makeBufferooList(2)
        stubUserRepositoryGetUsers(Flowable.just(users))
        val testObserver = getUsers.buildUseCaseObservable(null).test()
        testObserver.assertValue(users)
    }

    private fun stubUserRepositoryGetUsers(single: Flowable<List<User>>) {
        whenever(mockUserRepository.getUsers())
                .thenReturn(single)
    }

}
