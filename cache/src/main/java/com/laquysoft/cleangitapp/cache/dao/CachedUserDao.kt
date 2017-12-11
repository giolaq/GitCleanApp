package com.laquysoft.cleangitapp.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.laquysoft.cleangitapp.cache.db.constants.CacheConstants
import com.laquysoft.cleangitapp.cache.model.CachedUser

@Dao
abstract class CachedUserDao {

    @Query(CacheConstants.QUERY_USERS)
    abstract fun getUsers(): List<CachedUser>

    @Query(CacheConstants.DELETE_ALL_USERS)
    abstract fun clearUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUser(cachedUser: CachedUser)

}
