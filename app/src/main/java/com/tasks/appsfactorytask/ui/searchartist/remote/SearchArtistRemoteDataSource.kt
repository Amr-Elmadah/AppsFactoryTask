package com.tasks.appsfactorytask.ui.searchartist.remote

import com.tasks.appsfactorytask.data.remote.network.response.ArtistSearchResponse
import com.tasks.appsfactorytask.data.remote.network.retrofit.LastFMServiceAPI
import com.tasks.appsfactorytask.util.Constants
import io.reactivex.Single
import javax.inject.Inject

class SearchArtistRemoteDataSource @Inject constructor(private val lastFMServiceAPI: LastFMServiceAPI) {

    fun search(artistName: String, page: Int, limit: Int): Single<ArtistSearchResponse> =
        lastFMServiceAPI.search(artist = artistName, apiKey = Constants.API_KEY, page = page, limit = limit)
}