package com.laquysoft.cleangitapp.domain.interactor.browse

import io.reactivex.Flowable
import com.laquysoft.cleangitapp.domain.executor.PostExecutionThread
import com.laquysoft.cleangitapp.domain.executor.ThreadExecutor
import com.laquysoft.cleangitapp.domain.interactor.FlowableUseCase
import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Use case used for retreiving a [List] of [User] instances from the [UserRepository]
 */
open class GetSearchUsers @Inject constructor(val userRepository: UserRepository,
                                              threadExecutor: ThreadExecutor,
                                              postExecutionThread: PostExecutionThread):
        FlowableUseCase<List<User>, String?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: String?): Flowable<List<User>> {
        return userRepository.getSearchUsers(params)
    }

}
