package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger

import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module.PicassoModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.retrofit.RandomUsersListApi
import com.squareup.picasso.Picasso
import dagger.Component

@Component(modules = [RandomUsersListApi::class, PicassoModule::class])
interface ListUserComponent {
    fun getRandomUsersService(): RandomUsersListApi
    fun getPicasso(): Picasso
}