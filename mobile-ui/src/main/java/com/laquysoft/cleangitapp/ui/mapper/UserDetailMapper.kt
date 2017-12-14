package com.laquysoft.cleangitapp.ui.mapper

import com.laquysoft.cleangitapp.presentation.model.UserDetailView
import com.laquysoft.cleangitapp.presentation.model.UserView
import com.laquysoft.cleangitapp.ui.model.UserDetailViewModel
import com.laquysoft.cleangitapp.ui.model.UserViewModel
import javax.inject.Inject

/**
 * Map a [UserDetailView] to and from a [UserDetailViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class UserDetailMapper @Inject constructor(): Mapper<UserDetailViewModel, UserDetailView> {

    /**
     * Map a [UserView] instance to a [UserViewModel] instance
     */
    override fun mapToViewModel(type: UserDetailView): UserDetailViewModel {
        return UserDetailViewModel(type.login, type.avatarUrl, type.name)
    }

    /**
     * Map a [UserViewModel] instance to a [UserView] instance
     */
    override fun mapToView(type: UserDetailViewModel): UserDetailView {
        return UserDetailView(type.login, type.avatarUrl, type.name)
    }

}
