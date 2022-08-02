package com.start.presentation.extension

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


fun Int.toPxF(): Float = this * Resources.getSystem().displayMetrics.density

fun Int.toPx(): Int = toPxF().toInt()

fun Float.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Float.toPxF(): Float = (this * Resources.getSystem().displayMetrics.density)

fun Context?.getDimension(dimenRes: Int): Int =
    this!!.resources.getDimension(dimenRes).toInt()

fun Context.getFloatDimension(dimenRes: Int): Float {
    val typedValue = TypedValue()
    resources.getValue(dimenRes, typedValue, true)
    return typedValue.float
}

fun View.doOnApplyWindowInsets(
    block: (
        view: View,
        insets: WindowInsetsCompat,
    ) -> WindowInsetsCompat,
) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        block(v, insets)
    }
}

fun WindowInsetsCompat.getBottomInsetAdjustNothing(): Int =
    WindowInsetsCompat(this).getInsets(WindowInsetsCompat.Type.systemBars()).bottom

val WindowInsetsCompat.maxInsetBottom: Int
    get() = maxInsets().bottom

val WindowInsetsCompat.maxInsetTop: Int
    get() = maxInsets().top

val WindowInsetsCompat.maxInsetLeft: Int
    get() = maxInsets().left

val WindowInsetsCompat.maxInsetRight: Int
    get() = maxInsets().right

private fun WindowInsetsCompat.maxInsets() = getInsets(
    WindowInsetsCompat.Type.statusBars() or
            WindowInsetsCompat.Type.navigationBars() or
            WindowInsetsCompat.Type.captionBar() or
            getKeyboardTypeIme()
)

private fun getKeyboardTypeIme() =
    WindowInsetsCompat.Type.ime()

fun setWindowsSoftInputMode(activity: Activity, mode: Int) {
    activity.window.setSoftInputMode(mode)
}