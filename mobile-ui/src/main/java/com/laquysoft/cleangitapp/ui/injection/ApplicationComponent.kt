package com.laquysoft.cleangitapp.ui.injection

import android.app.Application
import com.laquysoft.cleangitapp.ui.CleanGitApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import org.buffer.android.boilerplate.ui.injection.module.ActivityBindingModule
import org.buffer.android.boilerplate.ui.injection.module.ApplicationModule
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication

@PerApplication
@Component(modules = arrayOf(ActivityBindingModule::class, ApplicationModule::class,
        AndroidSupportInjectionModule::class))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: CleanGitApplication)

}
