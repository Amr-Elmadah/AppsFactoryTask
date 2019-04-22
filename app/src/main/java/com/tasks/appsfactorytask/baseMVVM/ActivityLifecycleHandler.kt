package com.tasks.appsfactorytask.baseMVVM

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tasks.appsfactorytask.util.Logger

class ActivityLifecycleHandler : Application.ActivityLifecycleCallbacks {

	override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
		if (activity is AppCompatActivity) {
			activity.supportFragmentManager.registerFragmentLifecycleCallbacks(FragmentLifecycleHandler, true)
			Logger.d(this, "${activity::class.java.simpleName} : Created")
		}
	}

	override fun onActivityStarted(activity: Activity?) {
	}

	override fun onActivityResumed(activity: Activity?) {
	}

	override fun onActivityPaused(activity: Activity?) {
	}

	override fun onActivityStopped(activity: Activity?) {
	}

	override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
	}

	override fun onActivityDestroyed(activity: Activity?) {
	}
}