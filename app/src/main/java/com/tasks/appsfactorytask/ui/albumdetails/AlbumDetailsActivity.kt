package com.tasks.appsfactorytask.ui.albumdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.tasks.appsfactorytask.R
import com.tasks.appsfactorytask.databinding.ActivityAlbumDetailsBinding
import com.tasks.appsfactorytask.ui.home.domain.entity.AlbumItem

class AlbumDetailsActivity : AppCompatActivity() {

    lateinit var activityAlbumDetailsBinding: ActivityAlbumDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAlbumDetailsBinding = setContentView(this, R.layout.activity_album_details)
        bindAlbum()
    }

    companion object {
        const val EXTRA_ALBUM = "album"
    }

    private fun bindAlbum() {
        val extras = intent.extras
        extras?.let {
            activityAlbumDetailsBinding.album = (it.getParcelable(EXTRA_ALBUM) as? AlbumItem)!!
        }
    }
}