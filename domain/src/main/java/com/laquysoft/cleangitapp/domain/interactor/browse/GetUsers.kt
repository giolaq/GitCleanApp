package com.laquysoft.cleangitapp.domain.interactor.browse

import io.reactivex.Flowable
import com.laquysoft.cleangitapp.domain.executor.PostExecutionThread
import com.laquysoft.cleangitapp.domain.executor.ThreadExecutor
import com.laquysoft.cleangitapp.domain.interactor.FlowableUseCase
import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Use case used for retreiving a [List] of [User] instances from the [BufferooRepository]
 */
open class GetUsers @Inject constructor(val userRepository: UserRepository,
                                        threadExecutor: ThreadExecutor,
                                        postExecutionThread: PostExecutionThread):
        FlowableUseCase<List<User>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Flowable<List<User>> {
        return userRepository.getUsers()
    }

}
