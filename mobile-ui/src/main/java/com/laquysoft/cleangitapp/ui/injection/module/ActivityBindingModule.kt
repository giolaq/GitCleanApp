package org.buffer.android.boilerplate.ui.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.laquysoft.cleangitapp.ui.browse.BrowseActivity
import com.laquysoft.cleangitapp.ui.detail.UserDetailActivity
import com.laquysoft.cleangitapp.ui.detail.UserDetailFragment
import org.buffer.android.boilerplate.ui.injection.scopes.PerActivity
import org.buffer.android.boilerplate.ui.injection.scopes.PerFragment

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(BrowseActivityModule::class))
    abstract fun bindMainActivity(): BrowseActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(DetailActivityModule::class))
    abstract fun bindDetailActivity(): UserDetailActivity

    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(UserDetailFragmentModule::class))
    abstract fun bindDetailFragment(): UserDetailFragment

}
