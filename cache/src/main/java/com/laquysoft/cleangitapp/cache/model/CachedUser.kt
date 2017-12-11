package com.laquysoft.cleangitapp.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.laquysoft.cleangitapp.cache.db.constants.CacheConstants

/**
 * Model used solely for the caching of a user
 */
@Entity(tableName = CacheConstants.TABLE_NAME)
data class CachedUser(

        @PrimaryKey
        var id: Int,
        val login: String,
        val avatarUrl: String
)
