package com.verdite.samples.reactivesearch.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.inputmethod.InputMethodManager

object Functions {
    inline fun <reified T> Context.start() {
        val finalClass: Class<*> = T::class.java
        val intent = Intent(this, finalClass)
        this.startActivity(intent)
    }

    fun Activity.hideKeyboard() {
        try {
            val inputManager = this.getSystemService(Context.INPUT_METHOD_SERVICE)
            (inputManager as? InputMethodManager)?.hideSoftInputFromWindow(
                this.currentFocus?.windowToken,
                0
            )
        } catch (e: Exception) {
            Log.e("KEYBOARD", e.message ?: "Unable to hide keyboard")
        }
    }
}