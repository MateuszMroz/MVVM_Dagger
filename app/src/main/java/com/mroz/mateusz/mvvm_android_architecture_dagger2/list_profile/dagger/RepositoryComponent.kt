package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger

import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.ViewModelModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.scope.RandomUserApplicationScope
import com.mroz.mateusz.mvvm_android_architecture_dagger2.db.dagger.UserDbModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.MainActivity
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module.RepoModule
import dagger.Component
import dagger.android.AndroidInjectionModule

@RandomUserApplicationScope
@Component(modules = [RepoModule::class, UserDbModule::class, ViewModelModule::class, AndroidInjectionModule::class])
interface RepositoryComponent {
    fun injectRepo(activity: MainActivity)
}