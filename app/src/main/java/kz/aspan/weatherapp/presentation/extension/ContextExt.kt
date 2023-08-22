package kz.aspan.weatherapp.presentation.extension

import android.content.Context
import android.util.DisplayMetrics

fun Context.dpToPixel(dp: Float): Int {
    return (dp * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}