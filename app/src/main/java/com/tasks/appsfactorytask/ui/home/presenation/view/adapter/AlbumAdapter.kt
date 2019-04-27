package com.tasks.appsfactorytask.ui.home.presenation.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tasks.appsfactorytask.R
import com.tasks.appsfactorytask.base.presentation.view.adapter.BaseRecyclerAdapter
import com.tasks.appsfactorytask.base.presentation.view.extension.getInflatedView
import com.tasks.appsfactorytask.base.presentation.view.extension.loadFromUrl
import com.tasks.appsfactorytask.ui.home.domain.entity.AlbumItem
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_artist_album.view.*

class AlbumAdapter : BaseRecyclerAdapter<AlbumItem>() {
    
    private val mViewClickSubject = PublishSubject.create<AlbumItem>()

    fun getViewClickedObservable(): Observable<AlbumItem> {
        return mViewClickSubject
    }

    override fun getAdapterPageSize(): Int {
        return PAGE_SIZE
    }

    override fun mainItemView(parent: ViewGroup): View {
        return parent.getInflatedView(R.layout.item_artist_album)
    }


    override fun mainItemViewHolder(view: View): RecyclerView.ViewHolder {
        return ArtistAlbumViewHolder(view)
    }

    override fun onBindMainViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArtistAlbumViewHolder) {
            holder.bind(getItems()[position])
            holder.itemView.setOnClickListener {
                mViewClickSubject.onNext(getItems()[position])
            }
        }
    }

    override fun footerItemView(parent: ViewGroup): View {
        return parent.getInflatedView(R.layout.layout_footer_progress_bar)
    }

    private class ArtistAlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: AlbumItem) = with(itemView) {
            tvAlbumName.text = item.albumName
            tvArtistName.text = item.artistName
            val image: String? = item.albumImage
            image?.let {
                imageArtistAlbum.loadFromUrl(it)
            }
        }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}