package com.tasks.appsfactorytask.ui.home.injection

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasks.appsfactorytask.data.local.dao.AlbumDao
import com.tasks.appsfactorytask.ui.home.domain.interactor.GetAlbumsLocalUseCase
import com.tasks.appsfactorytask.ui.home.domain.repository.HomeRepository
import com.tasks.appsfactorytask.ui.home.domain.repository.HomeRepositoryImp
import com.tasks.appsfactorytask.ui.home.local.HomeLocalDataSource
import com.tasks.appsfactorytask.ui.home.presenation.view.adapter.AlbumAdapter
import com.tasks.appsfactorytask.ui.home.presenation.viewmodel.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun provideHomeLocalDataSource(albumDao: AlbumDao) =
        HomeLocalDataSource(albumDao)

    @Provides
    fun provideHomeRepository(homeLocalDataSource: HomeLocalDataSource): HomeRepository =
        HomeRepositoryImp(homeLocalDataSource)

    @Provides
    fun provideGetAlbumsLocalUseCase(repository: HomeRepository) =
        GetAlbumsLocalUseCase(repository)

    @Provides
    fun provideHomeViewModel(localUseCase: GetAlbumsLocalUseCase) =
        HomeViewModel(localUseCase)

    @Provides
    fun provideAlbumAdapter() =
        AlbumAdapter()

    @Provides
    fun provideLinearLayoutManager(context: Context) =
        LinearLayoutManager(context)
}