package com.mroz.mateusz.mvvm_android_architecture_dagger2.retrofit

import android.app.Person
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RandomUsersListApi {
    @GET
    fun getRandomUsers(@Query("results") size: Int): Call<List<Person>>
}