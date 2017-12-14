package com.laquysoft.cleangitapp.cache.db.constants

/**
 * Defines constants for the Tables
 */
object CacheConstants {

    const val TABLE_NAME = "users"

    const val QUERY_USERS = "SELECT * FROM" + " " + TABLE_NAME

    const val QUERY_USER = "SELECT * FROM" + " " + TABLE_NAME + " WHERE login LIKE :login"

    const val DELETE_ALL_USERS = "DELETE FROM" + " " + TABLE_NAME

    const val DETAILS_TABLE_NAME = "usersdetails"

    const val QUERY_USERS_DETAILS = "SELECT * FROM" + " " + DETAILS_TABLE_NAME

    const val QUERY_USER_DETAILS = "SELECT * FROM" + " " + DETAILS_TABLE_NAME + " WHERE login LIKE :login"

    const val DELETE_ALL_USERS_DETAILS = "DELETE FROM" + " " + DETAILS_TABLE_NAME

}
