package com.tasks.appsfactorytask.ui.searchartist.domain

import com.tasks.appsfactorytask.ui.searchartist.remote.apiparams.SearchParams
import com.tasks.appsfactorytask.base.domain.interactor.SingleUseCase
import com.tasks.appsfactorytask.data.remote.network.response.Artist
import io.reactivex.Single
import javax.inject.Inject

class SearchArtistUseCase @Inject constructor(private val repository: SearchArtistRepository) :
    SingleUseCase<SearchParams, List<Artist>>() {
    override fun build(params: SearchParams): Single<List<Artist>> =
        repository.searchArtist(artistName = params.artistName, page = params.page, limit = params.limit)

}