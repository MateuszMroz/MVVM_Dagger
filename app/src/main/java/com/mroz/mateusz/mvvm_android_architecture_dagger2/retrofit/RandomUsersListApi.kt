package com.mroz.mateusz.mvvm_android_architecture_dagger2.retrofit

import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RandomUsersListApi {
    @GET("api")
    fun getRandomUsers(@Query("results") size: Int): Call<User>
}