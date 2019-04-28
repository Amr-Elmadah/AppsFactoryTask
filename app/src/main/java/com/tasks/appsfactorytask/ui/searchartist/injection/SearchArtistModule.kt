package com.tasks.appsfactorytask.ui.searchartist.injection

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasks.appsfactorytask.data.remote.network.retrofit.LastFMServiceAPI
import com.tasks.appsfactorytask.ui.searchartist.domain.SearchArtistRepository
import com.tasks.appsfactorytask.ui.searchartist.domain.SearchArtistRepositoryImp
import com.tasks.appsfactorytask.ui.searchartist.domain.SearchArtistUseCase
import com.tasks.appsfactorytask.ui.searchartist.presentation.view.adapter.ArtistsAdapter
import com.tasks.appsfactorytask.ui.searchartist.presentation.viewmodel.SearchArtistViewModel
import com.tasks.appsfactorytask.ui.searchartist.remote.SearchArtistRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class SearchArtistModule {
    @Provides
    fun provideArtistAlbumsRemoteDataSource(lastFMServiceAPI: LastFMServiceAPI) =
        SearchArtistRemoteDataSource(lastFMServiceAPI = lastFMServiceAPI)

    @Provides
    fun provideSearchArtistRepository(artistRemoteDataSource: SearchArtistRemoteDataSource): SearchArtistRepository =
        SearchArtistRepositoryImp(artistRemoteDataSource)

    @Provides
    fun provideSearchArtistUseCase(searchArtistRepository: SearchArtistRepository) =
        SearchArtistUseCase(searchArtistRepository)

    @Provides
    fun provideSearchArtistViewmodel(userCase: SearchArtistUseCase): SearchArtistViewModel =
        SearchArtistViewModel(userCase)

    @Provides
    fun provideLinearLayoutManager(context: Context) =
        LinearLayoutManager(context)

    @Provides
    fun provideArtistListAdapter() =
        ArtistsAdapter()

}