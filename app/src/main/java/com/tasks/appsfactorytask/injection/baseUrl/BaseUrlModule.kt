package com.tasks.appsfactorytask.injection.baseUrl

import com.tasks.appsfactorytask.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class BaseUrlModule {

    object DAGGER_CONSTANTS {
        const val BASE_URL = "baseUrlString"
    }

    private object ApiEndpointsConstants {
        const val ProductionURL = ""
        const val StagingURL = ""
    }

    @Provides
    @Singleton
    @Named(value = DAGGER_CONSTANTS.BASE_URL)
    fun providesBaseUrl() =
        if (BuildConfig.QA) ApiEndpointsConstants.StagingURL else ApiEndpointsConstants.ProductionURL
}