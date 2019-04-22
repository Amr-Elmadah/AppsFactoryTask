package com.tasks.appsfactorytask.extension

import android.widget.TextView

val TextView.textString: String
	get() = text.toString()
