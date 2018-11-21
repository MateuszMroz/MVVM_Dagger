package com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api


interface RequestCallback<T> {
    fun onSuccess(response:T)
    fun onFailure(message: String, code:Int)
}