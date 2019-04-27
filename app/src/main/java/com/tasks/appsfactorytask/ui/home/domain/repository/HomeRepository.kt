package com.tasks.appsfactorytask.ui.home.domain.repository

import androidx.lifecycle.LiveData
import com.tasks.appsfactorytask.data.local.entity.AlbumEntity

interface HomeRepository {
    fun getCachedAlbums(): LiveData<List<AlbumEntity>>
}