package com.tasks.appsfactorytask.ui.searchartist.domain

import com.tasks.appsfactorytask.ui.searchartist.remote.SearchArtistRemoteDataSource
import com.tasks.appsfactorytask.data.remote.network.response.Artist
import io.reactivex.Single
import javax.inject.Inject

class SearchArtistRepositoryImp @Inject constructor(private val artistRemoteDataSource: SearchArtistRemoteDataSource) :
    SearchArtistRepository {
    override fun searchArtist(artistName: String, page: Int, limit: Int): Single<List<Artist>> {
        return artistRemoteDataSource.search(artistName = artistName, page = page, limit = limit).map {
            it.results.artistMatches.artists
        }
    }
}