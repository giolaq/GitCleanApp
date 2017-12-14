package com.laquysoft.cleangitapp.domain.model

/**
 * Representation for a [UserDetail] fetched from an external layer data source
 */
data class UserDetail(val id: Int, val login: String, val avatarUrl: String, val name: String,
                            val followers: List<String>,
                            val repositories: List<String>)