package org.buffer.android.boilerplate.ui.injection.module

import dagger.Module
import dagger.Provides
import com.laquysoft.cleangitapp.domain.interactor.browse.GetUsers
import com.laquysoft.cleangitapp.domain.interactor.detail.GetDetail
import com.laquysoft.cleangitapp.presentation.browse.BrowseUserDetailViewModelFactory
import com.laquysoft.cleangitapp.presentation.browse.BrowseUsersViewModelFactory
import com.laquysoft.cleangitapp.presentation.mapper.UserDetailMapper
import com.laquysoft.cleangitapp.presentation.mapper.UserMapper


/**
 * Module used to provide dependencies at an fragment-level.
 */
@Module
open class UserDetailFragmentModule {



    @Provides
    fun provideBrowseUsersDetailViewModelFactory(getDetail: GetDetail,
                                                 userDetailMapper: UserDetailMapper):
            BrowseUserDetailViewModelFactory {
        return BrowseUserDetailViewModelFactory(getDetail, userDetailMapper)
    }


}
