package com.tasks.appsfactorytask.ui.searchartist.presentation.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.tasks.appsfactorytask.R
import com.tasks.appsfactorytask.base.presentation.view.adapter.BaseRecyclerAdapter
import com.tasks.appsfactorytask.base.presentation.view.extension.afterTextChanged
import com.tasks.appsfactorytask.base.presentation.view.extension.setVisible
import com.tasks.appsfactorytask.base.presentation.view.extension.showSnack
import com.tasks.appsfactorytask.base.presentation.viewmodel.ViewModelFactory
import com.tasks.appsfactorytask.ui.artistablums.presetation.view.activity.ArtistAlbumsActivity
import com.tasks.appsfactorytask.ui.searchartist.presentation.view.adapter.ArtistsAdapter
import com.tasks.appsfactorytask.ui.searchartist.presentation.viewmodel.SearchArtistViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search_artist.*
import javax.inject.Inject

class SearchArtistActivity : AppCompatActivity(), BaseRecyclerAdapter.OnLoadMoreListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<SearchArtistViewModel>

    private val mViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SearchArtistViewModel::class.java)
    }

    @Inject
    lateinit var manager: LinearLayoutManager

    @Inject
    lateinit var adapter: ArtistsAdapter

    var isNewSearch = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_search_artist)
        setupControllers()
    }

    private fun setupControllers() {
        supportActionBar?.title = getString(R.string.search_artists)

        llSearch.isEnabled = false
        llSearch.setOnClickListener {
            isNewSearch = true
            mViewModel.search(artistName = etSearch.text.toString(), isNewSearch = isNewSearch)
        }

        etSearch.afterTextChanged {
            llSearch.isEnabled = it.isNotEmpty()
        }

        setupRecyclerView()
        observeSearchResultsChange()
    }

    private fun setupRecyclerView() {
        manager.orientation = RecyclerView.VERTICAL
        adapter.addOnLoadMoreListener(this, rvArtist, manager)
        rvArtist.layoutManager = manager
        rvArtist.adapter = adapter
    }

    @SuppressLint("CheckResult")
    private fun observeSearchResultsChange() {
        mViewModel.mArtistList.observe(this, Observer {
            it?.let {
                if (!isNewSearch) {
                    adapter.addMoreItems(it.toMutableList())
                } else {
                    adapter.getItems().clear()
                    adapter.notifyDataSetChanged()
                    adapter.addMoreItemsFirst(it.toMutableList())
                }
                layoutNoData.setVisible(isNewSearch && it.isEmpty())
                rvArtist.setVisible((isNewSearch && !it.isEmpty()) || !isNewSearch)
            }
        })

        adapter.getViewClickedObservable().subscribe {
            openAlbumsActivity(it)
        }

        mViewModel.search().observe(this, successObserver = Observer {
        },
            loadingObserver = Observer { it ->
                it?.let {
                    progress.setVisible(it)
                }
            }, commonErrorObserver = Observer {

            }, networkErrorConsumer = Observer {
                llMainContent.showSnack(getString(R.string.internet_connection), Snackbar.LENGTH_LONG)
            })
    }

    private fun openAlbumsActivity(artistName: String) {
        val intent = Intent(this, ArtistAlbumsActivity::class.java)
        intent.putExtra(ArtistAlbumsActivity.EXTRA_ARTIST_NAME, artistName)
        startActivity(intent)
    }

    override fun onLoadMore() {
        isNewSearch = false
        mViewModel.search(artistName = etSearch.text.toString(), isNewSearch = isNewSearch)
    }
}
