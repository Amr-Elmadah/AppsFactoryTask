package com.tasks.appsfactorytask.ui.home.domain.interactor

import androidx.lifecycle.LiveData
import com.tasks.appsfactorytask.ui.home.domain.repository.HomeRepository
import com.tasks.appsfactorytask.data.local.entity.AlbumEntity
import javax.inject.Inject

class GetAlbumsLocalUseCase @Inject constructor(private val repository: HomeRepository) {
    fun build(): LiveData<List<AlbumEntity>> =
        repository.getCachedAlbums()
}