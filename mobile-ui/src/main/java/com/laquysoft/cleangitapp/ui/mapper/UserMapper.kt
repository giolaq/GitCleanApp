package com.laquysoft.cleangitapp.ui.mapper

import com.laquysoft.cleangitapp.presentation.model.UserView
import com.laquysoft.cleangitapp.ui.model.UserViewModel
import javax.inject.Inject

/**
 * Map a [UserView] to and from a [UserViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class UserMapper @Inject constructor(): Mapper<UserViewModel, UserView> {

    /**
     * Map a [UserView] instance to a [UserViewModel] instance
     */
    override fun mapToViewModel(type: UserView): UserViewModel {
        return UserViewModel(type.login, type.avatarUrl)
    }

    /**
     * Map a [UserViewModel] instance to a [UserView] instance
     */
    override fun mapToView(type: UserViewModel): UserView {
        return UserView(type.login, type.avatarUrl)
    }

}
