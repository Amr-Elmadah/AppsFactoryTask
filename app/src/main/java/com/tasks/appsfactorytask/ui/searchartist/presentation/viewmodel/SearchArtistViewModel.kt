package com.tasks.appsfactorytask.ui.searchartist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tasks.appsfactorytask.base.domain.exception.AppsFactoryException
import com.tasks.appsfactorytask.base.presentation.model.ObservableResource
import com.tasks.appsfactorytask.base.presentation.viewmodel.BaseViewModel
import com.tasks.appsfactorytask.data.remote.network.response.Artist
import com.tasks.appsfactorytask.ui.searchartist.domain.SearchArtistUseCase
import com.tasks.appsfactorytask.ui.searchartist.remote.apiparams.SearchParams
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchArtistViewModel @Inject constructor(private val searchArtistUseCase: SearchArtistUseCase) :
    BaseViewModel() {
    private val mSearchObservable by lazy { ObservableResource<List<Artist>>() }
    var mArtists: ArrayList<Artist> = ArrayList()
    val mArtistList = MutableLiveData<List<Artist>>()

    fun search(artistName: String = "", isNewSearch: Boolean = true): ObservableResource<List<Artist>> {
        if (artistName.isNotEmpty()) {
            if (isNewSearch) {
                mArtists = ArrayList()
            }
            addDisposable(searchArtistUseCase.build(
                params = SearchParams(
                    artistName = artistName,
                    page = (mArtists.size / 20) + 1
                )
            )
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    mSearchObservable.loading.postValue(true)
                }
                .doAfterTerminate {
                    mSearchObservable.loading.postValue(false)
                }
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                    {
                        mArtists.addAll(it)
                        mArtistList.value = it
                    }, { error ->
                        (error as? AppsFactoryException).let {
                            mSearchObservable.error.postValue(it)
                        }
                    }
                ))
        }
        return mSearchObservable
    }
}