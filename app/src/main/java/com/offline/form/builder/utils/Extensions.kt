package com.offline.form.builder.utils

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("show")
fun show(view: View, isVisible: Boolean){
    view.isVisible = isVisible
}