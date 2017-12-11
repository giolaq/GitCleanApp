package com.laquysoft.cleangitapp.data.model

/**
 * Representation for a [UserEntity] fetched from an external layer data source
 */
data class UserEntity(val id: Int, val login: String, val avatarUrl: String)
