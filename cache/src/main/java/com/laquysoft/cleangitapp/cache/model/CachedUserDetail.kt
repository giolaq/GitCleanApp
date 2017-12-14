package com.laquysoft.cleangitapp.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.laquysoft.cleangitapp.cache.db.constants.CacheConstants

/**
 * Model used solely for the caching of a user detail
 *
 */
@Entity(tableName = CacheConstants.DETAILS_TABLE_NAME)
data class CachedUserDetail(

        @PrimaryKey
        var id: Int,
        val login: String,
        val avatarUrl: String,
        val name: String,
        val followers: StringList,
        val repositories: StringList
)
