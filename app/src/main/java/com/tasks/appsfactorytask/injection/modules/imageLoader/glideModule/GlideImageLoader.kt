package com.tasks.appsfactorytask.injection.modules.imageLoader.glideModule

import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.tasks.appsfactorytask.R
import javax.inject.Inject

class GlideImageLoader @Inject constructor(private val base_url: String) : ImageLoader {

	private val mDefaultImageLoaderPlaceholder = R.mipmap.ic_launcher

	override fun loadImageInto(targetImageView: ImageView) {
		loadImageInto(mDefaultImageLoaderPlaceholder, targetImageView)
	}

	override fun loadImageInto(placeholderImageId: Int, targetImageView: ImageView) {
		GlideApp.with(targetImageView.context).load(base_url).placeholder(placeholderImageId)
				.centerCrop()
				.into(targetImageView)
	}

	override fun loadCircleImageInto(placeholderImageId: Int, targetImageView: ImageView) {
		GlideApp.with(targetImageView.context).load(base_url).placeholder(placeholderImageId)
				.transform(CircleCrop())
				.into(targetImageView)
	}
}