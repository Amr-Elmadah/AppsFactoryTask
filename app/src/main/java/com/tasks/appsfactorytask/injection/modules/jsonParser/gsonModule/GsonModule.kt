package com.tasks.appsfactorytask.injection.modules.jsonParser.gsonModule

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GsonModule {

	private val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

	@Provides
	@Singleton
	fun providesGson() = GsonBuilder().setDateFormat(DATE_FORMAT).create()!!
}