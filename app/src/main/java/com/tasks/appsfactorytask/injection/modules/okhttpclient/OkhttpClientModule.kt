package com.tasks.appsfactorytask.injection.modules.okhttpclient

import com.tasks.appsfactorytask.BuildConfig
import com.tasks.appsfactorytask.injection.modules.jsonParser.gsonModule.GsonModule
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [GsonModule::class])
class OkhttpClientModule {

	private val interceptor = Interceptor {
		var original = it.request()
		val originalHttpUrl = original.url()
		val url = originalHttpUrl
				.newBuilder()
//                .addQueryParameter("lang", lang)
				.build()
		val requestBuilder = original.newBuilder().url(url)
		requestBuilder.addHeader("Content-Type", "application/json")
		original = if (original.header("AppInternal-NoAuth") == null) {
			requestBuilder
					.build()
		} else {
			requestBuilder
					.removeHeader("AppInternal-NoAuth")
					.build()
		}

		it.proceed(original)
	}

	@Provides
	fun provideLoggingInterceptor() =
			if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) else HttpLoggingInterceptor().setLevel(
					HttpLoggingInterceptor.Level.NONE
			)

	@Provides
	@Singleton
	fun providesOkHttpClient(
			cache: Cache,
			loggingInterceptor: HttpLoggingInterceptor
	): OkHttpClient =
			OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).writeTimeout(
					60,
					TimeUnit.SECONDS
			).cache(cache).addInterceptor(interceptor).addInterceptor(loggingInterceptor).build()
}