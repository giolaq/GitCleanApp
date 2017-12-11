package com.laquysoft.cleangitapp.remote.mapper

import com.laquysoft.cleangitapp.remote.model.UserModel
import com.laquysoft.cleangitapp.data.model.UserEntity
import javax.inject.Inject

/**
 * Map a [UserModel] to and from a [BufferooEntity] instance when data is moving between
 * this later and the Data layer
 */
open class UserEntityMapper @Inject constructor(): EntityMapper<UserModel, UserEntity> {

    /**
     * Map an instance of a [UserModel] to a [BufferooEntity] model
     */
    override fun mapFromRemote(type: UserModel): UserEntity {
        return UserEntity(type.id, type.login, type.avatarUrl)
    }

}
