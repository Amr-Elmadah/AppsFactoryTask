package com.tasks.appsfactorytask.injection.context

import com.tasks.appsfactorytask.ui.home.injection.HomeModule
import com.tasks.appsfactorytask.ui.home.presenation.view.activity.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {
//    @ContributesAndroidInjector(modules = [(SearchActivityModule::class)])
//    abstract fun bindSearchActivity(): SearchArtistActivity
//
//    @ContributesAndroidInjector(modules = [(ArtistTopAlbumsModule::class)])
//    abstract fun bindArtistTopAlbumsActivity(): TopArtistAlbumsActivity
//
    @ContributesAndroidInjector(modules = [(HomeModule::class)])
    abstract fun bindMainScreenActivity(): HomeActivity

}

