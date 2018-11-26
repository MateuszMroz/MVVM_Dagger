package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module

import android.app.Application
import android.content.Context
import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.scope.RandomUserApplicationScope
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository.ListUserRepository
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel.ListProfileViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule(var application:Application) {

    @Provides
    fun application(): Application = application

    @Provides
    fun repo(application:Application):ListUserRepository = ListUserRepository(application)

    @Provides
    fun viewModelFactory(repository: ListUserRepository): ListProfileViewModelFactory = ListProfileViewModelFactory(repository)
}