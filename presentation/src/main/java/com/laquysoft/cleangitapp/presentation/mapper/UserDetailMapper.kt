package com.laquysoft.cleangitapp.presentation.mapper

import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.domain.model.UserDetail
import com.laquysoft.cleangitapp.presentation.model.UserDetailView
import com.laquysoft.cleangitapp.presentation.model.UserView
import javax.inject.Inject

/**
 * Map a [UserDetailView] to and from a [UserDetail] instance when data is moving between
 * this layer and the Domain layer
 */
open class UserDetailMapper @Inject constructor(): Mapper<UserDetailView, UserDetail> {

    /**
     * Map a [UserDetail] instance to a [UserDetailView] instance
     */
    override fun mapToView(type: UserDetail): UserDetailView {
        return UserDetailView(type.login, type.avatarUrl, type.name)
    }


}
