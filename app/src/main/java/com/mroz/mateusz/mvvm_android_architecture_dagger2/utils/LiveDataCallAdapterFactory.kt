package com.mroz.mateusz.mvvm_android_architecture_dagger2.utils

import android.arch.lifecycle.LiveData
import android.text.Editable
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.ApiResponse
import okhttp3.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.IllegalArgumentException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


class LiveDataCallAdapterFactory: CallAdapter.Factory() {
    override fun get(returnType: Type,
                     annotations: Array<Annotation>,
                     retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if(CallAdapter.Factory.getRawType(returnType) != LiveData::class.java) return null
        val observableType = CallAdapter.Factory.getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = CallAdapter.Factory.getRawType(observableType)
        if(rawObservableType != ApiResponse::class.java) {
            throw IllegalArgumentException("type must be a resource")
        }
        if(observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }
        val bodyType = CallAdapter.Factory.getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter<Any>(bodyType)
    }
}