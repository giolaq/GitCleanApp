package com.laquysoft.cleangitapp.cache.mapper

import com.laquysoft.cleangitapp.cache.model.CachedUser
import com.laquysoft.cleangitapp.data.model.UserEntity
import javax.inject.Inject

/**
 * Map a [CachedUser] instance to and from a [UserEntity] instance when data is moving between
 * this later and the Data layer
 */
open class UserEntityMapper @Inject constructor():
        EntityMapper<CachedUser, UserEntity> {

    /**
     * Map a [UserEntity] instance to a [CachedUser] instance
     */
    override fun mapToCached(type: UserEntity): CachedUser {
        return CachedUser(type.id, type.login, type.avatarUrl)
    }

    /**
     * Map a [CachedUser] instance to a [UserEntity] instance
     */
    override fun mapFromCached(type: CachedUser): UserEntity {
        return UserEntity(type.id, type.login, type.avatarUrl)
    }

}
