package com.laquysoft.cleangitapp.cache.db.constants

/**
 * Defines constants for the users Table
 */
object CacheConstants {

    const val TABLE_NAME = "users"

    const val QUERY_USERS = "SELECT * FROM" + " " + TABLE_NAME

    const val QUERY_USER = "SELECT * FROM" + " " + TABLE_NAME + " WHERE login LIKE :login"

    const val DELETE_ALL_USERS = "DELETE FROM" + " " + TABLE_NAME

}
