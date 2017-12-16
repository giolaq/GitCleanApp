package com.laquysoft.cleangitapp.cache

import com.laquysoft.cleangitapp.cache.db.UsersDatabase
import com.laquysoft.cleangitapp.cache.mapper.UserDetailEntityMapper
import com.laquysoft.cleangitapp.cache.mapper.UserEntityMapper
import com.laquysoft.cleangitapp.data.model.UserDetailEntity
import com.laquysoft.cleangitapp.data.model.UserEntity
import com.laquysoft.cleangitapp.data.repository.UserCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving User instances. This class implements the
 * [UserCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class UserCacheImpl @Inject constructor(val usersDatabase: UsersDatabase,
                                        private val entityMapper: UserEntityMapper,
                                        private val entityUserDetailMapper: UserDetailEntityMapper,
                                        private val preferencesHelper: PreferencesHelper) :
        UserCache {


    override fun getSearchUsers(q: String?): Flowable<List<UserEntity>> {
        return Flowable.defer {
            Flowable.just(usersDatabase.cachedUserDao().getUsers())
        }.map {
            it.map { entityMapper.mapFromCached(it) }
        }
    }


    private val EXPIRATION_TIME = /*(60 * 10 * */1000.toLong()


    override fun saveUser(user: UserDetailEntity): Completable {
        return Completable.defer {
            usersDatabase.cachedUserDetailsDao().insertUser(
                    entityUserDetailMapper.mapToCached(user))
            Completable.complete()
        }
    }

    override fun getUser(login: String?): Flowable<UserDetailEntity> {
        return Flowable.defer {
            Flowable.just(usersDatabase.cachedUserDetailsDao().getUser(login))
        }.map {
            entityUserDetailMapper.mapFromCached(it)
        }
    }


    /**
     * Retrieve an instance from the database, used for tests.
     */
    internal fun getDatabase(): UsersDatabase {
        return usersDatabase
    }

    /**
     * Remove all the data from all the tables in the database.
     */
    override fun clearUsers(): Completable {
        return Completable.defer {
            usersDatabase.cachedUserDao().clearUsers()
            Completable.complete()
        }
    }

    /**
     * Save the given list of [UserEntity] instances to the database.
     */
    override fun saveUsers(users: List<UserEntity>): Completable {
        return Completable.defer {
            users.forEach {
                usersDatabase.cachedUserDao().insertUser(
                        entityMapper.mapToCached(it))
            }
            Completable.complete()
        }
    }

    /**
     * Retrieve a list of [UserEntity] instances from the database.
     */
    override fun getUsers(): Flowable<List<UserEntity>> {
        return Flowable.defer {
            Flowable.just(usersDatabase.cachedUserDao().getUsers())
        }.map {
            it.map { entityMapper.mapFromCached(it) }
        }
    }

    /**
     * Check whether there are instances of [CachedUser] stored in the cache.
     */
    override fun isCached(): Single<Boolean> {
        return Single.defer {
            Single.just(usersDatabase.cachedUserDao().getUsers().isNotEmpty())
        }
    }

    /**
     * Set a point in time at when the cache was last updated.
     */
    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time.
     */
    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

}
