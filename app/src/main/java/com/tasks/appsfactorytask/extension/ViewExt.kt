package com.tasks.appsfactorytask.extension

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

fun View.getParentActivity(): AppCompatActivity? {
	var context = this.context
	while (context is ContextWrapper) {
		if (context is AppCompatActivity) {
			return context
		}
		context = context.baseContext
	}
	View.VISIBLE
	return null
}

fun View.visible() {
	visibility = View.VISIBLE
}

fun View.gone() {
	visibility = View.GONE
}

fun View.toggleViability() {
	if (this.visibility == View.VISIBLE) {
		this.visibility = View.GONE
	} else {
		this.visibility = View.VISIBLE
	}
}
