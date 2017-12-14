package com.laquysoft.cleangitapp.data.model

/**
 * Representation for a [UserDetailEntity] fetched from an external layer data source
 */
data class UserDetailEntity(val id: Int, val login: String, val avatarUrl: String, val name: String,
                            val followers: List<String>,
                            val repositories: List<String>)
