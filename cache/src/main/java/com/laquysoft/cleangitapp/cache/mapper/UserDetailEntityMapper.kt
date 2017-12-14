package com.laquysoft.cleangitapp.cache.mapper

import com.laquysoft.cleangitapp.cache.model.CachedUserDetail
import com.laquysoft.cleangitapp.cache.model.StringList
import com.laquysoft.cleangitapp.data.model.UserDetailEntity
import javax.inject.Inject

/**
 * Map a [CachedUserDetail] instance to and from a [UserDetailEntity] instance when data is moving between
 * this later and the Data layer
 */
open class UserDetailEntityMapper @Inject constructor() :
        EntityMapper<CachedUserDetail, UserDetailEntity> {

    /**
     * Map a [UserDetailEntity] instance to a [CachedUserDetail] instance
     */
    override fun mapToCached(type: UserDetailEntity): CachedUserDetail {
        return CachedUserDetail(type.id, type.login, type.avatarUrl,
                type.name, StringList(type.followers), StringList(type.repositories))
    }

    /**
     * Map a [CachedUserDetail] instance to a [UserDetailEntity] instance
     */
    override fun mapFromCached(type: CachedUserDetail): UserDetailEntity {
        return UserDetailEntity(type.id, type.login, type.avatarUrl,
                type.name, type.followers.list, type.repositories.list)
    }

}
