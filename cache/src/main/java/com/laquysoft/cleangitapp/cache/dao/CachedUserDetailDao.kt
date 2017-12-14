package com.laquysoft.cleangitapp.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.laquysoft.cleangitapp.cache.db.constants.CacheConstants
import com.laquysoft.cleangitapp.cache.model.CachedUserDetail

@Dao
abstract class CachedUserDetailDao {

    @Query(CacheConstants.QUERY_USERS_DETAILS)
    abstract fun getUsers(): List<CachedUserDetail>

    @Query(CacheConstants.QUERY_USER_DETAILS)
    abstract fun getUser(login: String?): CachedUserDetail

    @Query(CacheConstants.DELETE_ALL_USERS_DETAILS)
    abstract fun clearUsersDetails()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUser(cachedUserDetail: CachedUserDetail)

}
