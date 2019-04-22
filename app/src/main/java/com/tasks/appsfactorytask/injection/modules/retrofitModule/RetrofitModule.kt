package com.tasks.appsfactorytask.injection.modules.retrofitModule

import com.tasks.appsfactorytask.injection.baseUrl.BaseUrlModule
import com.tasks.appsfactorytask.injection.modules.jsonParser.gsonModule.GsonModule
import com.tasks.appsfactorytask.injection.modules.okhttpclient.OkhttpClientModule
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [GsonModule::class, BaseUrlModule::class, OkhttpClientModule::class])
class RetrofitModule {

	@Provides
	@Singleton
	fun gsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

	@Provides
	@Singleton
	fun providesRetrofitInstance(
        @Named(value = BaseUrlModule.DAGGER_CONSTANTS.BASE_URL) baseUrl: String,
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
	) =
			Retrofit.Builder().baseUrl(baseUrl).client(client).addConverterFactory(gsonConverterFactory).build()
}