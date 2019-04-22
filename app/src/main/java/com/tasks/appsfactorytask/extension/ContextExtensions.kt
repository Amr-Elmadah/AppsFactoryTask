package com.tasks.appsfactorytask.extension

import android.content.Context
import android.util.TypedValue

fun Context.convertDpTpPx(value: Float): Float {
	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)
}

fun Context.convertSpTpPx(value: Float): Float {
	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, resources.displayMetrics)
}