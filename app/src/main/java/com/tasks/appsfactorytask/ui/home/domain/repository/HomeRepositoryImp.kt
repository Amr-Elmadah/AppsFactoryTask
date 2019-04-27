package com.tasks.appsfactorytask.ui.home.domain.repository

import androidx.lifecycle.LiveData
import com.tasks.appsfactorytask.data.local.entity.AlbumEntity
import com.tasks.appsfactorytask.ui.home.local.HomeLocalDataSource
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(private val localDataSource: HomeLocalDataSource) :
    HomeRepository {
    override fun getCachedAlbums(): LiveData<List<AlbumEntity>> =
        localDataSource.getAllAlbums()
}