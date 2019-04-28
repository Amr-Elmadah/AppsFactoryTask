package com.tasks.appsfactorytask.ui.searchartist.domain

import com.tasks.appsfactorytask.data.remote.network.response.Artist
import io.reactivex.Single

interface SearchArtistRepository {
    fun searchArtist(artistName: String, page: Int, limit: Int): Single<List<Artist>>
}