package com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.dagger.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.scope.RandomUserApplicationScope
import com.mroz.mateusz.mvvm_android_architecture_dagger2.utils.BASE_URL
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.RandomUsersListApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [OkHttpClientModule::class])
class RandomUsersListModule {

    @Provides
    fun randomUsersApi(retrofit: Retrofit): RandomUsersListApi = retrofit.create(RandomUsersListApi::class.java)

    @RandomUserApplicationScope
    @Provides
    fun retrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory,
                 gson: Gson):Retrofit {

        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    fun gson():Gson = GsonBuilder().create()

    @Provides
    fun gsonConvertFactory(gson: Gson) : GsonConverterFactory = GsonConverterFactory.create(gson)


}