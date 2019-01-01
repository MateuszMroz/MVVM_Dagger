package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger

import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module.PicassoModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.dagger.module.RandomUsersListModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.scope.RandomUserApplicationScope
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.RandomUsersListApi
import dagger.Component

@RandomUserApplicationScope
@Component(modules = [RandomUsersListModule::class, PicassoModule::class])
interface ListUserComponent {
    fun getRandomUsersService(): RandomUsersListApi
}