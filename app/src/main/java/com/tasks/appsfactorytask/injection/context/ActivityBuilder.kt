package com.tasks.appsfactorytask.injection.context

import com.tasks.appsfactorytask.ui.artistablums.injection.ArtistAlbumsModule
import com.tasks.appsfactorytask.ui.artistablums.presetation.view.activity.ArtistAlbumsActivity
import com.tasks.appsfactorytask.ui.home.injection.HomeModule
import com.tasks.appsfactorytask.ui.home.presenation.view.activity.HomeActivity
import com.tasks.appsfactorytask.ui.searchartist.injection.SearchArtistModule
import com.tasks.appsfactorytask.ui.searchartist.presentation.view.activity.SearchArtistActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(HomeModule::class)])
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [(SearchArtistModule::class)])
    abstract fun bindSearchActivity(): SearchArtistActivity

    @ContributesAndroidInjector(modules = [(ArtistAlbumsModule::class)])
    abstract fun bindArtistAlbumActivity(): ArtistAlbumsActivity
}

