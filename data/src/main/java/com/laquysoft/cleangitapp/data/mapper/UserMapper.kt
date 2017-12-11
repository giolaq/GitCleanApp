package com.laquysoft.cleangitapp.data.mapper

import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.data.model.UserEntity
import javax.inject.Inject


/**
 * Map a [UserEntity] to and from a [Bufferoo] instance when data is moving between
 * this later and the Domain layer
 */
open class UserMapper @Inject constructor(): Mapper<UserEntity, User> {

    /**
     * Map a [UserEntity] instance to a [Bufferoo] instance
     */
    override fun mapFromEntity(type: UserEntity): User {
        return User(type.id, type.login, type.avatarUrl)
    }

    /**
     * Map a [User] instance to a [BufferooEntity] instance
     */
    override fun mapToEntity(type: User): UserEntity {
        return UserEntity(type.id, type.login, type.avatarUrl)
    }


}
