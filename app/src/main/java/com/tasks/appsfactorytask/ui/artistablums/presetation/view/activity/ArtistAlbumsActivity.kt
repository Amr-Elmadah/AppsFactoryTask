package com.tasks.appsfactorytask.ui.artistablums.presetation.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.tasks.appsfactorytask.R
import com.tasks.appsfactorytask.base.presentation.view.extension.setVisible
import com.tasks.appsfactorytask.base.presentation.view.extension.showSnack
import com.tasks.appsfactorytask.base.presentation.viewmodel.ViewModelFactory
import com.tasks.appsfactorytask.ui.albumdetails.AlbumDetailsActivity
import com.tasks.appsfactorytask.ui.artistablums.presetation.view.adapter.ArtistAlbumAdapter
import com.tasks.appsfactorytask.ui.artistablums.presetation.viewmodel.ArtistAlbumsViewModel
import com.tasks.appsfactorytask.ui.home.domain.entity.AlbumItem
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_artist_albums.*
import javax.inject.Inject

class ArtistAlbumsActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ArtistAlbumsViewModel>


    private val mViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ArtistAlbumsViewModel::class.java)
    }

    @Inject
    lateinit var manager: LinearLayoutManager

    @Inject
    lateinit var adapter: ArtistAlbumAdapter

    companion object {
        const val EXTRA_ARTIST_NAME: String = "artist_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_artist_albums)
        setupControllers()
    }

    override fun onStart() {
        super.onStart()
        getArtistAlbums()
    }

    private fun setupControllers() {
        setupToolbar()
        setupRecyclerView()
        observeArtistAlbumsChange()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.miSave) {
                mViewModel.saveArtistAlbum()
            } else {
                mViewModel.deleteArtistAlbum()
            }
            false
        }
    }

    private fun getArtistAlbums() {
        val extras: Bundle? = intent.extras
        extras?.let {
            val artistName = it.getString(EXTRA_ARTIST_NAME)
            (artistName!!.isNotEmpty()).let {
                supportActionBar?.title = artistName
                mViewModel.getArtistAlbums(artistName = artistName)
            }
        }
    }

    private fun setupRecyclerView() {
        manager.orientation = RecyclerView.VERTICAL
        rvArtistAlbums.layoutManager = manager
        rvArtistAlbums.adapter = adapter
    }

    @SuppressLint("CheckResult")
    private fun observeArtistAlbumsChange() {
        mViewModel.mArtistAlbums.observe(this, Observer { albums ->
            albums?.let {
                adapter.addMoreItemsFirst(it.toMutableList())
            }
        })
        mViewModel.mArtistAlbumsObservable.observe(this,
            successObserver = Observer {
                it?.let {
                    llMainContent.showSnack(it)

                }
            }, commonErrorObserver = Observer {
            }, loadingObserver = Observer {
                it?.let {
                    progress.setVisible(it)
                }
            }, networkErrorConsumer = Observer {
                llMainContent.showSnack(getString(R.string.internet_connection), Snackbar.LENGTH_LONG)
            })

        adapter.getViewClickedObservable().subscribe {
            it?.let {
                openAlbumDetailsActivity(it)
            }
        }
    }

    private fun openAlbumDetailsActivity(album: AlbumItem) {
        val intent = Intent(this, AlbumDetailsActivity::class.java)
        intent.putExtra(AlbumDetailsActivity.EXTRA_ALBUM, album)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.artist_albums_menu, menu)
        return true
    }
}
