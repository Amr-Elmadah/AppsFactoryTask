package com.tasks.appsfactorytask.ui.home.presenation.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tasks.appsfactorytask.R
import com.tasks.appsfactorytask.base.presentation.viewmodel.ViewModelFactory
import com.tasks.appsfactorytask.ui.home.domain.entity.AlbumItem
import com.tasks.appsfactorytask.ui.home.local.mapToUI
import com.tasks.appsfactorytask.ui.home.presenation.view.adapter.AlbumAdapter
import com.tasks.appsfactorytask.ui.home.presenation.viewmodel.HomeViewModel
import com.tasks.appsfactorytask.ui.searchartist.presentation.view.activity.SearchArtistActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<HomeViewModel>

    @Inject
    lateinit var manager: LinearLayoutManager

    @Inject
    lateinit var adapter: AlbumAdapter

    private val mViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_home)
        setupControllers()
    }

    private fun setupControllers() {
        supportActionBar?.title = getString(R.string.home)
        btnSearch.setOnClickListener {
            openSearch()
        }
        setupAlbumsRecyclerView()
        observeCachedAlbumsChange()
    }

    private fun setupAlbumsRecyclerView() {
        manager.orientation = RecyclerView.VERTICAL
        rvAlbums.layoutManager = manager
        rvAlbums.adapter = adapter
    }

    @SuppressLint("CheckResult")
    private fun observeCachedAlbumsChange() {
        mViewModel.getAllStoredLocalAlbums().observe(this, Observer { it ->
            if (it != null) {
                if (it.isNotEmpty()) {
                    llNoData.visibility = View.GONE
                    adapter.addMoreItemsFirst(it.map { it.mapToUI() }.toMutableList())
                } else {
                    adapter.getItems().clear()
                    adapter.notifyDataSetChanged()
                    llNoData.visibility = View.VISIBLE
                }
            }
        })

        adapter.getViewClickedObservable().subscribe {
            openAlbumDetails(it)
        }
    }


    private fun openSearch() {
        startActivity(Intent(this, SearchArtistActivity::class.java))
    }

    private fun openAlbumDetails(album: AlbumItem) {
        //Todo: Navigate to details
    }
}