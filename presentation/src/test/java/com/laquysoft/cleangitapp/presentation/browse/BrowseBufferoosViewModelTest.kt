package com.laquysoft.cleangitapp.presentation.browse

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.*
import io.reactivex.subscribers.DisposableSubscriber
import com.laquysoft.cleangitapp.domain.interactor.browse.GetUsers
import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.presentation.data.ResourceState
import com.laquysoft.cleangitapp.presentation.mapper.UserMapper
import com.laquysoft.cleangitapp.presentation.model.UserView
import com.laquysoft.cleangitapp.presentation.test.factory.UserFactory
import com.laquysoft.cleangitapp.presentation.test.factory.DataFactory
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import org.mockito.Mock

@RunWith(JUnit4::class)
class BrowseUsersViewModelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var getUsers: GetUsers
    @Mock lateinit var userMapper: UserMapper

    @Captor
    private lateinit var captor: KArgumentCaptor<DisposableSubscriber<List<User>>>

    private lateinit var usersViewModel: BrowseUsersViewModel

    @Before
    fun setUp() {
        captor = argumentCaptor<DisposableSubscriber<List<User>>>()
        getUsers = mock()
        userMapper = mock()
        usersViewModel = BrowseUsersViewModel(getUsers, userMapper)
    }

    @Test
    fun getUsersExecutesUseCase() {
        usersViewModel.getUsers()

        verify(getUsers, times(1)).execute(any(), anyOrNull())
    }

    //<editor-fold desc="Success">
    @Test
    fun getUsersReturnsSuccess() {
        val list = UserFactory.makeBufferooList(2)
        val viewList = UserFactory.makeBufferooViewList(2)
        stubUserMapperMapToView(viewList[0], list[0])
        stubUserMapperMapToView(viewList[1], list[1])

        usersViewModel.getUsers()

        verify(getUsers).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(list)

        assert(usersViewModel.getUsers().value?.status == ResourceState.SUCCESS)
    }

    @Test
    fun getUsersReturnsDataOnSuccess() {
        val list = UserFactory.makeBufferooList(2)
        val viewList = UserFactory.makeBufferooViewList(2)

        stubUserMapperMapToView(viewList[0], list[0])
        stubUserMapperMapToView(viewList[1], list[1])

        usersViewModel.getUsers()

        verify(getUsers).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(list)

        assert(usersViewModel.getUsers().value?.data == viewList)
    }

    @Test
    fun getUsersReturnsNoMessageOnSuccess() {
        val list = UserFactory.makeBufferooList(2)
        val viewList = UserFactory.makeBufferooViewList(2)

        stubUserMapperMapToView(viewList[0], list[0])
        stubUserMapperMapToView(viewList[1], list[1])

        usersViewModel.getUsers()

        verify(getUsers).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(list)

        assert(usersViewModel.getUsers().value?.message == null)
    }
    //</editor-fold>

    //<editor-fold desc="Error">
    @Test
    fun getUsersReturnsError() {
        usersViewModel.getUsers()

        verify(getUsers).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assert(usersViewModel.getUsers().value?.status == ResourceState.ERROR)
    }

    @Test
    fun getUsersFailsAndContainsNoData() {
        usersViewModel.getUsers()

        verify(getUsers).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assert(usersViewModel.getUsers().value?.data == null)
    }

    @Test
    fun getUsersFailsAndContainsMessage() {
        val errorMessage = DataFactory.randomUuid()
        usersViewModel.getUsers()

        verify(getUsers).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assert(usersViewModel.getUsers().value?.message == errorMessage)
    }
    //</editor-fold>

    //<editor-fold desc="Loading">
    @Test
    fun getUsersReturnsLoading() {
        usersViewModel.getUsers()

        assert(usersViewModel.getUsers().value?.status == ResourceState.LOADING)
    }

    @Test
    fun getUsersContainsNoDataWhenLoading() {
        usersViewModel.getUsers()

        assert(usersViewModel.getUsers().value?.data == null)
    }

    @Test
    fun getUsersContainsNoMessageWhenLoading() {
        usersViewModel.getUsers()

        assert(usersViewModel.getUsers().value?.data == null)
    }
    //</editor-fold>

    private fun stubUserMapperMapToView(userView: UserView,
                                            user: User) {
        whenever(userMapper.mapToView(user))
                .thenReturn(userView)
    }

}
