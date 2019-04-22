package com.tasks.appsfactorytask.injection.modules.imageLoader.glideModule

import android.widget.ImageView

interface ImageLoader {

	fun loadImageInto(targetImageView: ImageView)

	fun loadImageInto(placeholderImageId: Int, targetImageView: ImageView)

	fun loadCircleImageInto(placeholderImageId: Int, targetImageView: ImageView)
}