package com.tasks.appsfactorytask.ui.artistablums.injection

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasks.appsfactorytask.data.local.dao.AlbumDao
import com.tasks.appsfactorytask.data.remote.network.retrofit.LastFMServiceAPI
import com.tasks.appsfactorytask.ui.artistablums.data.local.ArtistAlbumsLocalDataSource
import com.tasks.appsfactorytask.ui.artistablums.data.remote.ArtistAlbumsRemoteDataSource
import com.tasks.appsfactorytask.ui.artistablums.domain.interactor.AddAlbumsLocalUseCase
import com.tasks.appsfactorytask.ui.artistablums.domain.interactor.DeleteAlbumsLocalUseCase
import com.tasks.appsfactorytask.ui.artistablums.domain.interactor.GetArtistAlbumsUseCase
import com.tasks.appsfactorytask.ui.artistablums.domain.repository.ArtistAlbumsRepository
import com.tasks.appsfactorytask.ui.artistablums.domain.repository.ArtistAlbumsRepositoryImp
import com.tasks.appsfactorytask.ui.artistablums.presetation.view.adapter.ArtistAlbumAdapter
import com.tasks.appsfactorytask.ui.artistablums.presetation.viewmodel.ArtistAlbumsViewModel
import dagger.Module
import dagger.Provides

@Module
class ArtistAlbumsModule {

    @Provides
    fun provideArtistAlbumsLocalDataSource(albumDao: AlbumDao) =
        ArtistAlbumsLocalDataSource(albumDao = albumDao)

    @Provides
    fun provideArtistAlbumsRemoteDataSource(lastFMServiceAPI: LastFMServiceAPI) =
        ArtistAlbumsRemoteDataSource(lastFMServiceAPI = lastFMServiceAPI)

    @Provides
    fun provideArtistAlbumRepository(
        remoteDataSource: ArtistAlbumsRemoteDataSource,
        localDataSource: ArtistAlbumsLocalDataSource
    ): ArtistAlbumsRepository =
        ArtistAlbumsRepositoryImp(remoteDataSource, localDataSource)

    @Provides
    fun provideGetArtistAlbumsUseCase(repository: ArtistAlbumsRepository) =
        GetArtistAlbumsUseCase(repository)

    @Provides
    fun provideAddAlbumsLocalUseCase(repository: ArtistAlbumsRepository) =
        AddAlbumsLocalUseCase(repository)

    @Provides
    fun provideDeleteAlbumsLocalUseCase(repository: ArtistAlbumsRepository) =
        DeleteAlbumsLocalUseCase(repository)


    @Provides
    fun provideArtistAlbumsViewModel(
        getArtistAlbumsUseCase: GetArtistAlbumsUseCase,
        addAlbumsLocalUseCase: AddAlbumsLocalUseCase,
        deleteAlbumsLocalUseCase: DeleteAlbumsLocalUseCase
    ) =
        ArtistAlbumsViewModel(getArtistAlbumsUseCase, addAlbumsLocalUseCase, deleteAlbumsLocalUseCase)

    @Provides
    fun provideLinearLayoutManager(context: Context) =
        LinearLayoutManager(context)

    @Provides
    fun provideArtistAlbumAdapter() =
        ArtistAlbumAdapter()

}