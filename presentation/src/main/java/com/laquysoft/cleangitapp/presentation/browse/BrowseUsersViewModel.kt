package com.laquysoft.cleangitapp.presentation.browse

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.subscribers.DisposableSubscriber
import com.laquysoft.cleangitapp.domain.interactor.browse.GetUsers
import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.presentation.data.Resource
import com.laquysoft.cleangitapp.presentation.data.ResourceState
import com.laquysoft.cleangitapp.presentation.mapper.UserMapper
import com.laquysoft.cleangitapp.presentation.model.UserView
import javax.inject.Inject

open class BrowseUsersViewModel @Inject internal constructor(
        private val getUsers: GetUsers,
        private val userMapper: UserMapper) : ViewModel() {

    private val usersLiveData: MutableLiveData<Resource<List<UserView>>> =
            MutableLiveData()

    init {
        fetchUsers()
    }

    override fun onCleared() {
        getUsers.dispose()
        super.onCleared()
    }

    fun getUsers(): LiveData<Resource<List<UserView>>> {
        return usersLiveData
    }

    fun fetchUsers() {
        usersLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getUsers.execute(UserSubscriber())
    }

    inner class UserSubscriber: DisposableSubscriber<List<User>>() {

        override fun onComplete() { }

        override fun onNext(t: List<User>) {
            Log.d("BrowseUserViewModel", "onNext subscriber")
            usersLiveData.postValue(Resource(ResourceState.SUCCESS,
                    t.map { userMapper.mapToView(it) }, null))
        }

        override fun onError(exception: Throwable) {
            usersLiveData.postValue(Resource(ResourceState.ERROR, null, exception.message))
        }

    }

}
