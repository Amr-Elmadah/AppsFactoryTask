package com.tasks.appsfactorytask.ui.home.local

import androidx.lifecycle.LiveData
import com.tasks.appsfactorytask.data.local.dao.AlbumDao
import com.tasks.appsfactorytask.data.local.entity.AlbumEntity
import javax.inject.Inject

class HomeLocalDataSource @Inject constructor(private val albumDao: AlbumDao) {

    fun getAllAlbums(): LiveData<List<AlbumEntity>> =
        albumDao.getAllAlbums()
}