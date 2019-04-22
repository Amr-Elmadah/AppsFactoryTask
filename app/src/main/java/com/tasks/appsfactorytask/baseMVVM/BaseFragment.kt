package com.tasks.appsfactorytask.baseMVVM

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tasks.appsfactorytask.App
import com.tasks.appsfactorytask.dataSource.RepositorySource
import com.tasks.appsfactorytask.dataSource.pref.AppPreferencesHelper
import javax.inject.Inject

abstract class BaseFragment : Fragment() {
	val TAG = this.javaClass.simpleName

	lateinit var factory: FactoryGenerator

	@Inject
	lateinit var mRepo: RepositorySource

	@Inject
	lateinit var mSharedPrf: AppPreferencesHelper

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		App.instance.component.inject(this)
		factory = FactoryGenerator(mRepo, mSharedPrf)
	}
}