package com.tasks.appsfactorytask.injection

import android.app.Application
import com.tasks.appsfactorytask.App
import com.tasks.appsfactorytask.injection.context.ActivityBuilder
import com.tasks.appsfactorytask.injection.modules.AppModule
import com.tasks.appsfactorytask.injection.modules.AlbumsDatabaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        AlbumsDatabaseModule::class]
)
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

}

