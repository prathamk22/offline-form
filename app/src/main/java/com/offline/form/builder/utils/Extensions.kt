package com.offline.form.builder.utils

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.List

@BindingAdapter("show")
fun show(view: View, isVisible: Boolean){
    view.isVisible = isVisible
}

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}

fun List<String>.joinToStringFromJava(): String{
    return joinToString()
}