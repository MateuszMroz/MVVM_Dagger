package com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api

import timber.log.Timber


class Resource<out T>(val status:Status, val data:T?, val message: String?) {
    companion object {
        fun <T>  success(data: T?): Resource<T> {
            Timber.d("SUCCESS")
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg:String, data: T?):Resource<T> {
            Timber.d("ERROR")
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T>? {
            Timber.d("LOADING")
            return Resource(Status.LOADING, data, null)
        }
    }
}