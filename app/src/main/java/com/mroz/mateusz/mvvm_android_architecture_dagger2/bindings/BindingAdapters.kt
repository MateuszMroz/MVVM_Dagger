package com.mroz.mateusz.mvvm_android_architecture_dagger2.bindings

import android.databinding.BindingAdapter
import android.view.View


object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if(show) View.VISIBLE else View.GONE
    }
}