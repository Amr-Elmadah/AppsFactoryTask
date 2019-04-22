package com.tasks.appsfactorytask.extension

import android.content.Context
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Do a fragment transaction,
 * Start the transaction, do the task then commit.
 *
 * @param func a function of [FragmentTransaction] to be applied.
 */
private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
	val fragmentTransaction = beginTransaction()
	fragmentTransaction.func()
	fragmentTransaction.commit()
}

/**
 * Add a fragment onto a view
 *
 * @param fragment a sub class of [Fragment] to be added.
 * @param id the resource id of the view that the fragment will be add on it
 */
fun FragmentActivity.addFragment(
		fragment: Fragment, @IdRes id: Int,
		transition: Int? = null,
		clearBackStack: Boolean = false
) {
	if (clearBackStack) {
		supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
	}
	supportFragmentManager.inTransaction {
		add(id, fragment, fragment::class.java.simpleName)
		if (transition != null) {
			setTransition(transition)
		}
	}
}

/**
 * Replace a fragment onto a view
 *
 * @param fragment a sub class of [Fragment] to replace another.
 * @param id the resource id of the view that the fragment will be replaced on it
 */
fun FragmentActivity.replaceFragment(
		fragment: Fragment, @IdRes id: Int,
		addToBackStack: Boolean = true,
		transition: Int? = null,
		clearBackStack: Boolean = false
) {
	if (clearBackStack) {
		supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
	}
	supportFragmentManager.inTransaction {
		replace(id, fragment, fragment::class.java.simpleName)
		if (addToBackStack) {
			addToBackStack(fragment::class.java.simpleName)
		}
		if (transition != null) {
			setTransition(transition)
		}
	}
}

/**
 * hide soft keyboard
 */
fun FragmentActivity.hideKeyboardIfShown() {
	currentFocus?.apply {
		val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
	}
}

/**
 * show soft keyboard
 */
fun FragmentActivity.showKeyboard() {
	val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
	imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun FragmentActivity.startActivityAndClearStack() {
	intent.addFlags(
			Intent.FLAG_ACTIVITY_CLEAR_TOP or
					Intent.FLAG_ACTIVITY_CLEAR_TASK or
					Intent.FLAG_ACTIVITY_NEW_TASK
	)
	startActivity(intent)
}