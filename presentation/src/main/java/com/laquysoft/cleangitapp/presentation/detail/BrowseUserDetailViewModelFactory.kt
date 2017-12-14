package com.laquysoft.cleangitapp.presentation.browse

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.laquysoft.cleangitapp.domain.interactor.detail.GetDetail
import com.laquysoft.cleangitapp.presentation.detail.BrowseUsersDetailViewModel
import com.laquysoft.cleangitapp.presentation.mapper.UserDetailMapper

open class BrowseUserDetailViewModelFactory(
        private val getDetail: GetDetail,
        private val userDetailMapper: UserDetailMapper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BrowseUsersDetailViewModel::class.java)) {
            return BrowseUsersDetailViewModel(getDetail, userDetailMapper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
