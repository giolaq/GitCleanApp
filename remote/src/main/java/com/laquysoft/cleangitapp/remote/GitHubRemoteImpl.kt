package com.laquysoft.cleangitapp.remote

import io.reactivex.Flowable
import com.laquysoft.cleangitapp.remote.mapper.UserEntityMapper
import com.laquysoft.cleangitapp.data.model.UserEntity
import com.laquysoft.cleangitapp.data.repository.UserRemote
import javax.inject.Inject

/**
 * Remote implementation for retrieving User instances. This class implements the
 * [UserRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class GitHubRemoteImpl @Inject constructor(private val userService: GitHubService,
                                           private val entityMapper: UserEntityMapper):
        UserRemote {

    /**
     * Retrieve a list of [UserEntity] instances from the [BufferooService].
     */
    override fun getUsers(): Flowable<List<UserEntity>> {
        return userService.getUsers()
                .map {
                    val entities = mutableListOf<UserEntity>()
                    it.forEach { entities.add(entityMapper.mapFromRemote(it)) }
                    entities
                }
    }

}
