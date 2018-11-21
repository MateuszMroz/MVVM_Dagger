package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module

import android.content.Context
import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.scope.RandomUserApplicationScope
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.dagger.module.OkHttpClientModule
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Named

@Module(includes = [OkHttpClientModule::class])
class PicassoModule {
    @RandomUserApplicationScope
    @Provides
    fun picasso(@Named("application_context")context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build()
    }

    @Provides
    fun okHttp3Downloader(okHttpClient: OkHttpClient): OkHttp3Downloader = OkHttp3Downloader(okHttpClient)
}