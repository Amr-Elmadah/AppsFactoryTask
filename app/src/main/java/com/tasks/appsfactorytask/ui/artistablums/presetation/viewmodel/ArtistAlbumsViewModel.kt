package com.tasks.appsfactorytask.ui.artistablums.presetation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tasks.appsfactorytask.base.domain.exception.AppsFactoryException
import com.tasks.appsfactorytask.base.presentation.model.ObservableResource
import com.tasks.appsfactorytask.base.presentation.viewmodel.BaseViewModel
import com.tasks.appsfactorytask.data.local.entity.AlbumEntity
import com.tasks.appsfactorytask.data.remote.network.response.Album
import com.tasks.appsfactorytask.ui.artistablums.domain.interactor.AddAlbumsLocalUseCase
import com.tasks.appsfactorytask.ui.artistablums.domain.interactor.DeleteAlbumsLocalUseCase
import com.tasks.appsfactorytask.ui.artistablums.domain.interactor.GetArtistAlbumsUseCase
import com.tasks.appsfactorytask.ui.home.domain.entity.AlbumItem
import com.tasks.appsfactorytask.ui.home.local.map
import com.tasks.appsfactorytask.ui.home.local.mapToUI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ArtistAlbumsViewModel @Inject constructor(
    private val getArtistAlbumsUseCase: GetArtistAlbumsUseCase
    , private val addAlbumsLocalUseCase: AddAlbumsLocalUseCase
    , private val deleteAlbumsLocalUseCase: DeleteAlbumsLocalUseCase
) : BaseViewModel() {
    private val albumsList = mutableListOf<Album>()
    val mArtistAlbums = MutableLiveData<List<AlbumItem>>()
    val mArtistAlbumsObservable = ObservableResource<String>()

    fun getArtistAlbums(artistName: String) {
        addDisposable(getArtistAlbumsUseCase.build(params = artistName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                mArtistAlbumsObservable.loading.postValue(true)
            }
            .doAfterTerminate {
                mArtistAlbumsObservable.loading.postValue(false)
            }
            .subscribe({
                it?.let {
                    if (it.artistAlbums?.album.isNotEmpty()) {
                        albumsList.addAll(it.artistAlbums.album.toMutableList())
                        mArtistAlbums.value = (it.artistAlbums.album.map { it.mapToUI() })
                    }
                }
            }, {
                (it as? AppsFactoryException).let {
                    mArtistAlbumsObservable.error.value = it
                }
            })
        )
    }

    fun saveArtistAlbum() {
        if (albumsList.isNotEmpty()) {
            saveArtistAlbumsInDB(albumsList.map { it.map() })
        } else {
            val message = "No albums found"
            mArtistAlbumsObservable.value = message
        }
    }

    fun deleteArtistAlbum() {
        if (albumsList.isNotEmpty()) {
            deleteArtistAlbumsFromDB(albumsList.map { it.map() })
        } else {
            val message = "No albums found"
            mArtistAlbumsObservable.value = message
        }
    }

    private fun saveArtistAlbumsInDB(albums: List<AlbumEntity>) {
        addDisposable(addAlbumsLocalUseCase.build(albums)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val message = "Albums have been saved successfully"
                mArtistAlbumsObservable.value = message
            }, {
                val message = "Unexpected Error"
                mArtistAlbumsObservable.value = message

            }
            ))
    }

    private fun deleteArtistAlbumsFromDB(albums: List<AlbumEntity>) {
        addDisposable(deleteAlbumsLocalUseCase.build(albums)
            .subscribeOn(Schedulers.io())

            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val message = "Albums have been deleted successfully"
                mArtistAlbumsObservable.value = message


            }, {
                val message = "Unexpected Error"
                mArtistAlbumsObservable.value = message

            }
            ))
    }
}