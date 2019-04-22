package com.tasks.appsfactorytask.dataSource.remoteDataSource

import com.tasks.appsfactorytask.dataSource.remoteDataSource.retrofit.RemoteDataSourceUsingRetrofit
import com.tasks.appsfactorytask.dataSource.remoteDataSource.retrofit.services.ApiService
import com.tasks.appsfactorytask.dataSource.remoteDataSource.retrofit.services.ServicesModule
import com.tasks.appsfactorytask.injection.modules.jsonParser.gsonModule.GsonModule
import com.tasks.appsfactorytask.dataSource.remoteDataSource.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ServicesModule::class, GsonModule::class])
class RemoteDataSourceModule {
	@Provides
	@Singleton
	fun provideRemoteDataSource(
			apiClient: ApiService
	): RemoteDataSource = RemoteDataSourceUsingRetrofit(
			apiClient
	)
}