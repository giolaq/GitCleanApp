package com.laquysoft.cleangitapp.remote.mapper

import com.laquysoft.cleangitapp.data.model.UserDetailEntity
import com.laquysoft.cleangitapp.remote.model.UserModel
import com.laquysoft.cleangitapp.data.model.UserEntity
import com.laquysoft.cleangitapp.remote.model.UserDetailModel
import javax.inject.Inject

/**
 * Map a [UserDetailModel] to and from a [UserDetailEntity] instance when data is moving between
 * this later and the Data layer
 */
open class UserDetailEntityMapper @Inject constructor(): EntityMapper<UserDetailModel, UserDetailEntity> {

    /**
     * Map an instance of a [UserDetailModel] to a [UserDetailEntity] model
     */
    override fun mapFromRemote(type: UserDetailModel): UserDetailEntity {
        return UserDetailEntity(type.id, type.login, type.avatarUrl, type.name, listOf("a, b, c"), listOf("d, e, f"))
    }

}
