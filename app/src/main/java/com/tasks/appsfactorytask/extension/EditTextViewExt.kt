package com.tasks.appsfactorytask.extension

import android.widget.EditText

fun isEditTextValid(vararg editTexts: EditText, message: String = ""): Boolean {
	var isValid = true
	for (editText in editTexts) {
		if (editText.text.toString().isBlank()) {
			editText.error = message
			isValid = false
		} else {
			editText.error = null
		}
	}
	return isValid
}

fun isPasswordValid(password: EditText, confirmPassword: EditText, message: String = ""): Boolean {

	var isValid = isEditTextValid(password, confirmPassword)
	if (!isValid) {
		return isValid
	}

	if (password.text.toString() != confirmPassword.text.toString()) {
		isValid = false
		password.error = message
		confirmPassword.error = message
	} else if (password.text.toString().length < 6 || confirmPassword.text.toString().length < 6) {
		password.error = "Password need to be 6 characters at last"
		confirmPassword.error = "Password need to be 6 characters at last"
	} else {
		isValid = true
		password.error = null
		confirmPassword.error = null
	}
	return isValid
}