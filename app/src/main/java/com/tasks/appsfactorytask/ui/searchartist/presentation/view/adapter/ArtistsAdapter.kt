package com.tasks.appsfactorytask.ui.searchartist.presentation.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tasks.appsfactorytask.R
import com.tasks.appsfactorytask.base.presentation.view.adapter.BaseRecyclerAdapter
import com.tasks.appsfactorytask.base.presentation.view.extension.getInflatedView
import com.tasks.appsfactorytask.base.presentation.view.extension.loadFromUrl
import com.tasks.appsfactorytask.data.remote.network.response.Artist
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_artist_list.view.*


class ArtistsAdapter : BaseRecyclerAdapter<Artist>() {

    private val mViewClickSubject = PublishSubject.create<String>()

    fun getViewClickedObservable(): Observable<String> {
        return mViewClickSubject
    }

    override fun getAdapterPageSize(): Int {
        return PAGE_SIZE
    }

    override fun mainItemView(parent: ViewGroup): View {
        return parent.getInflatedView(R.layout.item_artist_list)
    }


    override fun mainItemViewHolder(view: View): RecyclerView.ViewHolder {
        return ArtistViewHolder(view)
    }

    override fun onBindMainViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArtistViewHolder) {
            holder.bind(getItems()[position])
            holder.itemView.setOnClickListener {
                mViewClickSubject.onNext(getItems()[position].name)
            }
        }
    }


    override fun footerItemView(parent: ViewGroup): View {
        return parent.getInflatedView(R.layout.layout_footer_progress_bar)
    }

    private class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Artist) = with(itemView) {
            tvArtistName.text = item.name
            tvArtistUrl.text = item.url
            item.image.forEach {
                if (it.size == "large")
                    imgArtist.loadFromUrl(it.text, isRounded = true, placeholder = R.drawable.bg_curved_primary)
            }
            tvListenersCount.text = item.listeners
            itemView.setOnClickListener {
            }
        }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}