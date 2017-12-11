package org.buffer.android.boilerplate.ui.injection.module

import dagger.Module
import dagger.Provides
import com.laquysoft.cleangitapp.domain.interactor.browse.GetUsers
import com.laquysoft.cleangitapp.presentation.browse.BrowseUsersViewModelFactory
import com.laquysoft.cleangitapp.presentation.mapper.UserMapper


/**
 * Module used to provide dependencies at an activity-level.
 */
@Module
open class BrowseActivityModule {


    @Provides
    fun provideBrowseUsersViewModelFactory(getUsers: GetUsers,
                                               usersMapper: UserMapper):
            BrowseUsersViewModelFactory {
        return BrowseUsersViewModelFactory(getUsers, usersMapper)
    }


}
