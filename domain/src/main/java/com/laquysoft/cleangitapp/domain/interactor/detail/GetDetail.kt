package com.laquysoft.cleangitapp.domain.interactor.detail

import com.laquysoft.cleangitapp.domain.executor.PostExecutionThread
import com.laquysoft.cleangitapp.domain.executor.ThreadExecutor
import com.laquysoft.cleangitapp.domain.interactor.FlowableUseCase
import com.laquysoft.cleangitapp.domain.model.UserDetail
import com.laquysoft.cleangitapp.domain.repository.UserRepository
import io.reactivex.Flowable
import javax.inject.Inject


/**
 * Use case used for retrieving detail of [UserDetail] instances from the [UserRepository]
 */
open class GetDetail @Inject constructor(val userRepository: UserRepository,
                                        threadExecutor: ThreadExecutor,
                                        postExecutionThread: PostExecutionThread):
        FlowableUseCase<UserDetail, String?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: String?): Flowable<UserDetail> {
        return userRepository.getUser(params)
    }

}
