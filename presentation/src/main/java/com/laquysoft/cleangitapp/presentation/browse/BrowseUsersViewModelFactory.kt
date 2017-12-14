package com.laquysoft.cleangitapp.presentation.browse

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.laquysoft.cleangitapp.domain.interactor.browse.GetUsers
import com.laquysoft.cleangitapp.presentation.detail.BrowseUsersDetailViewModel
import com.laquysoft.cleangitapp.presentation.mapper.UserMapper

open class BrowseUsersViewModelFactory(
        private val getUsers: GetUsers,
        private val userMapper: UserMapper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BrowseUsersViewModel::class.java)) {
            return BrowseUsersViewModel(getUsers, userMapper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
