package com.tasks.appsfactorytask.baseMVVM

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tasks.appsfactorytask.App
import com.tasks.appsfactorytask.dataSource.RepositorySource
import com.tasks.appsfactorytask.dataSource.pref.AppPreferencesHelper
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {
	val context by lazy { this }
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