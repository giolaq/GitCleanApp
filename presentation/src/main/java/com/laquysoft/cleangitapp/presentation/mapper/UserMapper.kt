package com.laquysoft.cleangitapp.presentation.mapper

import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.presentation.model.UserView
import javax.inject.Inject

/**
 * Map a [UserView] to and from a [Bufferoo] instance when data is moving between
 * this layer and the Domain layer
 */
open class UserMapper @Inject constructor(): Mapper<UserView, User> {

    /**
     * Map a [User] instance to a [BufferooView] instance
     */
    override fun mapToView(type: User): UserView {
        return UserView(type.login, type.avatarUrl)
    }


}
