package com.tasks.appsfactorytask.dataSource.remoteDataSource.retrofit.services

import com.tasks.appsfactorytask.injection.modules.retrofitModule.RetrofitModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class ServicesModule {
	@Provides
	@Singleton
	fun provideApiClient(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}