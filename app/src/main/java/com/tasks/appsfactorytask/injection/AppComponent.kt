package com.tasks.appsfactorytask.injection

import com.tasks.appsfactorytask.baseMVVM.BaseActivity
import com.tasks.appsfactorytask.baseMVVM.BaseDialogFragment
import com.tasks.appsfactorytask.baseMVVM.BaseFragment
import com.tasks.appsfactorytask.dataSource.RepositorySourceModule
import com.tasks.appsfactorytask.dataSource.remoteDataSource.RemoteDataSourceModule
import com.tasks.appsfactorytask.injection.baseUrl.BaseUrlModule
import com.tasks.appsfactorytask.injection.context.ContextModule
import com.tasks.appsfactorytask.injection.modules.cacheModule.CacheModule
import com.tasks.appsfactorytask.injection.modules.database.PrefModule
import com.tasks.appsfactorytask.injection.modules.imageLoader.glideModule.GlideModule
import com.tasks.appsfactorytask.injection.modules.jsonParser.gsonModule.GsonModule
import com.tasks.appsfactorytask.injection.modules.okhttpclient.OkhttpClientModule
import com.tasks.appsfactorytask.injection.modules.retrofitModule.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        BaseUrlModule::class,
        ContextModule::class,
        CacheModule::class,
        PrefModule::class,
        GlideModule::class,
        GsonModule::class,
        OkhttpClientModule::class,
        RetrofitModule::class,
        RepositorySourceModule::class,
        RemoteDataSourceModule::class
    ]
)

interface AppComponent {
    fun inject(baseFragment: BaseFragment)
    fun inject(baseActivity: BaseActivity)
    fun inject(baseDialogFragment: BaseDialogFragment)
}
