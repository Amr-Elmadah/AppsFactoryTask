package com.tasks.appsfactorytask.ui.artistablums.data.local

import com.tasks.appsfactorytask.data.local.dao.AlbumDao
import com.tasks.appsfactorytask.data.local.entity.AlbumEntity
import io.reactivex.Single
import javax.inject.Inject

class ArtistAlbumsLocalDataSource @Inject constructor(private val albumDao: AlbumDao) {

    fun insertArtistAlbums(albums: List<AlbumEntity>): Single<Boolean> =
        Single.create {
            albumDao.insertAlbums(albums)
            it.onSuccess(true)
        }


    fun deleteArtistAlbums(albums: List<AlbumEntity>): Single<Boolean> =
        Single.create {
            albumDao.deleteAlbums(albums)
            it.onSuccess(true)
        }


}