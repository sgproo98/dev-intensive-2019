package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard(){
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
}

fun Activity.isKeyboardOpen(){
    TODO()
}

fun Activity.isKeyboardClosed(){
    TODO()
}