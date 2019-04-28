package com.tasks.appsfactorytask.ui.artistablums.data.remote

import com.tasks.appsfactorytask.data.remote.network.response.ArtistAlbumsResponse
import com.tasks.appsfactorytask.data.remote.network.retrofit.LastFMServiceAPI
import com.tasks.appsfactorytask.util.Constants
import io.reactivex.Single
import javax.inject.Inject

class ArtistAlbumsRemoteDataSource @Inject constructor(private val lastFMServiceAPI: LastFMServiceAPI) {

    fun getArtistAlbums(artistName: String): Single<ArtistAlbumsResponse> =
        lastFMServiceAPI.getArtistAlbums(artist = artistName, apiKey = Constants.API_KEY)
}