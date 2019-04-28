package com.tasks.appsfactorytask.ui.artistablums.domain.interactor

import com.tasks.appsfactorytask.base.domain.interactor.SingleUseCase
import com.tasks.appsfactorytask.data.local.entity.AlbumEntity
import com.tasks.appsfactorytask.ui.artistablums.domain.repository.ArtistAlbumsRepository
import io.reactivex.Single
import javax.inject.Inject

class DeleteAlbumsLocalUseCase @Inject constructor(private val repository: ArtistAlbumsRepository) :
    SingleUseCase<List<AlbumEntity>, Boolean>() {
    override fun build(params: List<AlbumEntity>): Single<Boolean> =
        repository.deleteArtistAlbums(artistAlbums = params)

}