package com.mroz.mateusz.mvvm_android_architecture_dagger2.retrofit

import retrofit2.Call
import retrofit2.Response


class CallBackKt<T>: retrofit2.Callback<T> {
    val CONNECTION_FAILURE:Int = 600
    val callback: RequestCallback<T>? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        callback!!.onFailure("ERROR", CONNECTION_FAILURE)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        response.body()?.let {
            if (response.isSuccessful) callback?.onSuccess(it)
            else {
                var errorMessage: String = response.errorBody()!!.string()
                callback!!.onFailure(errorMessage, response.code())
            }
        }
    }
}