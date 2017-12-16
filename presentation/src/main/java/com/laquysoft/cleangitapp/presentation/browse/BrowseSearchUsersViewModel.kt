package com.laquysoft.cleangitapp.presentation.browse

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.laquysoft.cleangitapp.domain.interactor.browse.GetSearchUsers
import com.laquysoft.cleangitapp.domain.interactor.browse.GetUsers
import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.presentation.architecture.SingleLiveEvent
import com.laquysoft.cleangitapp.presentation.data.Resource
import com.laquysoft.cleangitapp.presentation.data.ResourceState
import com.laquysoft.cleangitapp.presentation.mapper.UserMapper
import com.laquysoft.cleangitapp.presentation.model.UserView
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

open class BrowseSearchUsersViewModel @Inject internal constructor(
        private val getUsers: GetSearchUsers,
        private val userMapper: UserMapper) : ViewModel() {

    private val usersLiveData: MutableLiveData<Resource<List<UserView>>> =
            MutableLiveData()

    private val openUserEvent = SingleLiveEvent<String>()

    init {
        //fetchUsers()
    }

    override fun onCleared() {
        getUsers.dispose()
        super.onCleared()
    }

    fun getUsers(q: String): LiveData<Resource<List<UserView>>> {
        fetchSearchUsers(q)
        return usersLiveData
    }

    fun getSearchUsers(q: String): LiveData<Resource<List<UserView>>> {
        return usersLiveData
    }

    fun getOpenUser(): SingleLiveEvent<String> {
        return openUserEvent
    }

    fun fetchSearchUsers(q: String) {
        usersLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getUsers.execute(UserSubscriber(), q)
    }

    inner class UserSubscriber : DisposableSubscriber<List<User>>() {

        override fun onComplete() {}

        override fun onNext(t: List<User>) {
            usersLiveData.postValue(Resource(ResourceState.SUCCESS,
                    t.map { userMapper.mapToView(it) }, null))
        }

        override fun onError(exception: Throwable) {
            usersLiveData.postValue(Resource(ResourceState.ERROR, null, exception.message))
        }

    }

    fun onUserClick(user: UserView) {
        openUserEvent.postValue(user.login)
    }

}
