package com.laquysoft.cleangitapp.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.laquysoft.cleangitapp.cache.dao.CachedUserDao
import com.laquysoft.cleangitapp.cache.dao.CachedUserDetailDao
import com.laquysoft.cleangitapp.cache.model.CachedUser
import com.laquysoft.cleangitapp.cache.model.CachedUserDetail
import javax.inject.Inject

@Database(entities = arrayOf(CachedUser::class, CachedUserDetail::class), version = 2)
@TypeConverters(StringListConverter::class)
abstract class UsersDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedUserDao(): CachedUserDao
    abstract fun cachedUserDetailsDao(): CachedUserDetailDao

    private var INSTANCE: UsersDatabase? = null

    private val sLock = Any()

    fun getInstance(context: Context): UsersDatabase {
        if (INSTANCE == null) {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            UsersDatabase::class.java, "cleangit.db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
                return INSTANCE!!
            }
        }
        return INSTANCE!!
    }

}
