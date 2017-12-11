package com.laquysoft.cleangitapp.ui.injection.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import com.laquysoft.cleangitapp.domain.executor.PostExecutionThread
import com.laquysoft.cleangitapp.domain.repository.UserRepository
import com.laquysoft.cleangitapp.ui.injection.ApplicationComponent
import org.buffer.android.boilerplate.ui.injection.module.ActivityBindingModule
import com.laquysoft.cleangitapp.ui.injection.module.TestApplicationModule
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication
import com.laquysoft.cleangitapp.ui.test.TestApplication

@Component(modules = arrayOf(TestApplicationModule::class, ActivityBindingModule::class,
        AndroidSupportInjectionModule::class))
@PerApplication
interface TestApplicationComponent : ApplicationComponent {

    fun userRepository(): UserRepository

    fun postExecutionThread(): PostExecutionThread

    fun inject(application: TestApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestApplicationComponent
    }

}
