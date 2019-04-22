package com.tasks.appsfactorytask.dataSource.pref

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(
		private val mSharedPreferences: SharedPreferences,
		private val mGson: Gson
) {

	fun clearSharedPref() {
		mSharedPreferences.edit().clear().apply()
	}
}