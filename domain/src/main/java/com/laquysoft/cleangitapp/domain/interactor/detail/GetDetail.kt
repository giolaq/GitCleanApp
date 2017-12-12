package com.laquysoft.cleangitapp.domain.interactor.detail

import com.laquysoft.cleangitapp.domain.executor.PostExecutionThread
import com.laquysoft.cleangitapp.domain.executor.ThreadExecutor
import com.laquysoft.cleangitapp.domain.interactor.FlowableUseCase
import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.domain.repository.UserRepository
import io.reactivex.Flowable
import javax.inject.Inject


/**
 * Use case used for retreiving detail of [User] instances from the [UserRepository]
 */
open class GetDetail @Inject constructor(val userRepository: UserRepository,
                                        threadExecutor: ThreadExecutor,
                                        postExecutionThread: PostExecutionThread):
        FlowableUseCase<User, String?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: String?): Flowable<User> {
        return userRepository.getUser(params)
    }

}
