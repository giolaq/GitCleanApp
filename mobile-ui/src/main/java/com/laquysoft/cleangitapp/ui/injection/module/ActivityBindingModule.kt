package org.buffer.android.boilerplate.ui.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.laquysoft.cleangitapp.ui.browse.BrowseActivity
import com.laquysoft.cleangitapp.ui.detail.UserDetailActivity
import org.buffer.android.boilerplate.ui.injection.scopes.PerActivity

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(BrowseActivityModule::class, DetailActivityModule::class))
    abstract fun bindMainActivity(): BrowseActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(DetailActivityModule::class))
    abstract fun bindDetailActivity(): UserDetailActivity

}
