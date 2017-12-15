package org.buffer.android.boilerplate.ui.injection.component

import dagger.Subcomponent
import dagger.android.AndroidInjector
import com.laquysoft.cleangitapp.ui.browse.BrowseActivity
import com.laquysoft.cleangitapp.ui.detail.UserDetailFragment

@Subcomponent
interface UserListFragmentSubComponent : AndroidInjector<UserDetailFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<UserDetailFragment>()

}
