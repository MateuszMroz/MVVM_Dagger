package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module

import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [OkHttpClientModule::class])
class RandomUsersListModule {
    val url:String = "https://randomuser.me/"

    @Provides
    fun randomUsersApi(retrofit: Retrofit): RandomUsersListModule = retrofit.create(RandomUsersListModule::class.java)

    @Provides
    fun retrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory,
                 gson: Gson):Retrofit {


        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(url)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    fun gson():Gson = GsonBuilder().create()

    @Provides
    fun gsonConvertFactory(gson: Gson) : GsonConverterFactory = GsonConverterFactory.create(gson)


}