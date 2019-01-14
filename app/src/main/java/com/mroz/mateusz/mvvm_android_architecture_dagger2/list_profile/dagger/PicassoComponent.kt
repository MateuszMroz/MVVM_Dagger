package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger

import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.scope.RandomUserApplicationScope
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module.PicassoModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module.PicassoModule_OkHttp3DownloaderFactory
import com.squareup.picasso.Picasso
import dagger.Component
@RandomUserApplicationScope
@Component(modules = [PicassoModule::class])
interface PicassoComponent {
    fun getPicasso(): Picasso
}