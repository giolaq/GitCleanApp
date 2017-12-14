package com.laquysoft.cleangitapp.data.mapper

import com.laquysoft.cleangitapp.data.model.UserDetailEntity
import com.laquysoft.cleangitapp.domain.model.UserDetail
import com.laquysoft.cleangitapp.data.model.UserEntity
import javax.inject.Inject


/**
 * Map a [UserEntity] to and from a [User] instance when data is moving between
 * this later and the Domain layer
 */
open class UserDetailMapper @Inject constructor(): Mapper<UserDetailEntity, UserDetail> {

    /**
     * Map a [UserEntity] instance to a [User] instance
     */
    override fun mapFromEntity(type: UserDetailEntity): UserDetail {
        return UserDetail(type.id, type.login, type.avatarUrl, type.name, type.followers, type.repositories)
    }

    /**
     * Map a [User] instance to a [UserDetailEntity] instance
     */
    override fun mapToEntity(type: UserDetail): UserDetailEntity {
        return UserDetailEntity(type.id, type.login, type.avatarUrl, type.name, type.followers, type.repositories)
    }


}
