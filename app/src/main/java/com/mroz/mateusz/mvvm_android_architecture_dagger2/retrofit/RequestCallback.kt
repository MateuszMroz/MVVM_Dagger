package com.mroz.mateusz.mvvm_android_architecture_dagger2.retrofit


interface RequestCallback<T> {
    fun onSuccess(response:T)
    fun onFailure(message: String, code:Int)
}