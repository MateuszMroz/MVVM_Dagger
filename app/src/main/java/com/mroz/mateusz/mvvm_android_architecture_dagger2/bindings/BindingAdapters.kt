package com.mroz.mateusz.mvvm_android_architecture_dagger2.bindings

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import timber.log.Timber


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if(show) View.VISIBLE else View.GONE
        Timber.d("visibleGone $show")
    }

    @JvmStatic
    @BindingAdapter("hideProgressBar")
    fun hide(refresh:SwipeRefreshLayout, show: Boolean) {
        refresh.isRefreshing = show
        Timber.d("hideProgressBar $show")
    }
}