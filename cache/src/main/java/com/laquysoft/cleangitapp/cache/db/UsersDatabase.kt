package com.laquysoft.cleangitapp.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.laquysoft.cleangitapp.cache.dao.CachedUserDao
import com.laquysoft.cleangitapp.cache.model.CachedUser
import javax.inject.Inject

@Database(entities = arrayOf(CachedUser::class), version = 1)
abstract class UsersDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedUserDao(): CachedUserDao

    private var INSTANCE: UsersDatabase? = null

    private val sLock = Any()

    fun getInstance(context: Context): UsersDatabase {
        if (INSTANCE == null) {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            UsersDatabase::class.java, "cleangit.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
        return INSTANCE!!
    }

}
