package com.laquysoft.cleangitapp.ui.mapper

import com.laquysoft.cleangitapp.presentation.model.UserView
import com.laquysoft.cleangitapp.ui.model.UserViewModel
import javax.inject.Inject

/**
 * Map a [UserView] to and from a [BufferooViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class UserMapper @Inject constructor(): Mapper<UserViewModel, UserView> {

    /**
     * Map a [UserView] instance to a [BufferooViewModel] instance
     */
    override fun mapToViewModel(type: UserView): UserViewModel {
        return UserViewModel(type.login, type.avatarUrl)
    }

}
