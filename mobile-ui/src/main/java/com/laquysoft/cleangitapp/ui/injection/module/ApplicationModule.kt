package org.buffer.android.boilerplate.ui.injection.module

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.content.Context
import com.laquysoft.cleangitapp.cache.PreferencesHelper
import com.laquysoft.cleangitapp.cache.UserCacheImpl
import com.laquysoft.cleangitapp.cache.db.UsersDatabase
import com.laquysoft.cleangitapp.cache.mapper.UserDetailEntityMapper
import com.laquysoft.cleangitapp.cache.mapper.UserEntityMapper
import com.laquysoft.cleangitapp.data.UserDataRepository
import com.laquysoft.cleangitapp.data.executor.JobExecutor
import com.laquysoft.cleangitapp.data.mapper.UserDetailMapper
import com.laquysoft.cleangitapp.data.mapper.UserMapper
import com.laquysoft.cleangitapp.data.repository.UserCache
import com.laquysoft.cleangitapp.data.repository.UserRemote
import com.laquysoft.cleangitapp.data.source.UserDataStoreFactory
import com.laquysoft.cleangitapp.domain.executor.PostExecutionThread
import com.laquysoft.cleangitapp.domain.executor.ThreadExecutor
import com.laquysoft.cleangitapp.domain.repository.UserRepository
import com.laquysoft.cleangitapp.remote.GitHubRemoteImpl
import com.laquysoft.cleangitapp.remote.GitHubService
import com.laquysoft.cleangitapp.remote.GitHubServiceFactory
import com.laquysoft.cleangitapp.ui.BuildConfig
import com.laquysoft.cleangitapp.ui.UiThread
import dagger.Module
import dagger.Provides
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }

    @Provides
    @PerApplication
    internal fun provideUserRepository(factory: UserDataStoreFactory,
                                       mapper: UserMapper,
                                       detailMapper: UserDetailMapper): UserRepository {
        return UserDataRepository(factory, mapper, detailMapper)
    }


    @Provides
    @PerApplication
    internal fun provideUserCache(database: UsersDatabase,
                                  entityMapper: UserEntityMapper,
                                  detailMapper: UserDetailEntityMapper,
                                  helper: PreferencesHelper): UserCache {
        return UserCacheImpl(database, entityMapper, detailMapper, helper)
    }

    @Provides
    @PerApplication
    internal fun provideUserRemote(service: GitHubService,
                                   factory: com.laquysoft.cleangitapp.remote.mapper.UserEntityMapper,
                                   detailFactory: com.laquysoft.cleangitapp.remote.mapper.UserDetailEntityMapper)
            : UserRemote {
        return GitHubRemoteImpl(service, factory, detailFactory)
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
    internal fun provideGithubService(): GitHubService {
        return GitHubServiceFactory.makeBuffeoorService(BuildConfig.DEBUG)
    }

    @Provides
    @PerApplication
    internal fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.NewInstanceFactory()
    }


    @Provides
    @PerApplication
    internal fun provideGithubDatabase(application: Application): UsersDatabase {
        return Room.databaseBuilder(application.applicationContext,
                UsersDatabase::class.java, "cleangit.db")
                .build()
    }

}
