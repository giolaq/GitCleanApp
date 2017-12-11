package com.laquysoft.cleangitapp.domain.model

/**
 * Representation for a [User] fetched from an external layer data source
 */
data class User(val id: Int, val login: String, val avatarUrl: String)
