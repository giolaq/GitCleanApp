package com.laquysoft.cleangitapp.presentation.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.laquysoft.cleangitapp.domain.interactor.detail.GetDetail
import com.laquysoft.cleangitapp.domain.model.UserDetail
import com.laquysoft.cleangitapp.presentation.data.Resource
import com.laquysoft.cleangitapp.presentation.data.ResourceState
import com.laquysoft.cleangitapp.presentation.mapper.UserDetailMapper
import com.laquysoft.cleangitapp.presentation.model.UserDetailView
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

open class BrowseUsersDetailViewModel @Inject internal constructor(
        private val getUserDetail: GetDetail,
        private val userDetailMapper: UserDetailMapper) : ViewModel() {

    private val usersLiveData: MutableLiveData<Resource<UserDetailView>> =
            MutableLiveData()

    init {
        //fetchDetail()
    }

    override fun onCleared() {
        getUserDetail.dispose()
        super.onCleared()
    }

    fun getUser(login: String): LiveData<Resource<UserDetailView>> {
        Log.d("BrowseUsersDetail", "getUser " + login)
        fetchDetail(login)
        return usersLiveData
    }

    fun fetchDetail(login: String) {
        Log.d("BrowseUsersDetail", "login " + login)
        usersLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getUserDetail.execute(UserSubscriber(), login)
    }

    inner class UserSubscriber : DisposableSubscriber<UserDetail>() {

        override fun onComplete() {}

        override fun onNext(t: UserDetail) {
            usersLiveData.postValue(Resource(ResourceState.SUCCESS,
                    userDetailMapper.mapToView(t), null))
        }

        override fun onError(exception: Throwable) {
            usersLiveData.postValue(Resource(ResourceState.ERROR, null, exception.message))
        }

    }

}
