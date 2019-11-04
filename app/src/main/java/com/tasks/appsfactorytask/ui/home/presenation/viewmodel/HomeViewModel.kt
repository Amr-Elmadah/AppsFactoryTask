package com.tasks.appsfactorytask.ui.home.presenation.viewmodel

import androidx.lifecycle.LiveData
import com.tasks.appsfactorytask.base.presentation.viewmodel.BaseViewModel
import com.tasks.appsfactorytask.data.local.entity.AlbumEntity
import com.tasks.appsfactorytask.ui.home.domain.interactor.GetAlbumsLocalUseCase
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getAlbumsUseCase: GetAlbumsLocalUseCase) :
    BaseViewModel() {

    fun getAllStoredLocalAlbums(): LiveData<List<AlbumEntity>> =
        runBlocking {
            return@runBlocking getAlbumsUseCase.build()
        }
}