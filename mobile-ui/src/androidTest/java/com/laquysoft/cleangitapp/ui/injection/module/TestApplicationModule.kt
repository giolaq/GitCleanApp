package com.laquysoft.cleangitapp.ui.injection.module

import android.app.Application
import android.content.Context
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import com.laquysoft.cleangitapp.cache.PreferencesHelper
import com.laquysoft.cleangitapp.data.executor.JobExecutor
import com.laquysoft.cleangitapp.domain.executor.PostExecutionThread
import com.laquysoft.cleangitapp.domain.executor.ThreadExecutor
import com.laquysoft.cleangitapp.domain.repository.UserRepository
import com.laquysoft.cleangitapp.remote.GitHubService
import com.laquysoft.cleangitapp.ui.UiThread
import com.laquysoft.cleangitapp.data.repository.UserCache
import com.laquysoft.cleangitapp.data.repository.UserRemote
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication

@Module
class TestApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(): PreferencesHelper {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideUserRepository(): UserRepository {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideUserCache(): UserCache {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideUserRemote(): UserRemote {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideUserService(): GitHubService {
        return mock()
    }

}
