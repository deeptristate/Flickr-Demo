package com.flickrdemo.helper

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.widget.Toast

fun Int.dpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

fun Int.pxToDp(): Int {
    return (this / Resources.getSystem().displayMetrics.density).toInt()
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.show(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}