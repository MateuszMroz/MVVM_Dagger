package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module

import android.app.Application
import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.scope.RandomUserApplicationScope
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository.ListUserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule(var application:Application) {
    @Provides
    fun repo():ListUserRepository = ListUserRepository(application)
}