package com.tasks.appsfactorytask.ui.artistablums.domain.interactor

import com.tasks.appsfactorytask.base.domain.interactor.SingleUseCase
import com.tasks.appsfactorytask.data.remote.network.response.ArtistAlbumsResponse
import com.tasks.appsfactorytask.ui.artistablums.domain.repository.ArtistAlbumsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetArtistAlbumsUseCase @Inject constructor(private val repository: ArtistAlbumsRepository) :
    SingleUseCase<String, ArtistAlbumsResponse>() {
    override fun build(params: String): Single<ArtistAlbumsResponse> =
        repository.getArtistAlbums(artistName = params)
}