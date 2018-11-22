package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger

import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module.RepoModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository.ListUserRepository
import dagger.Component


@Component(modules = arrayOf(RepoModule::class))
interface RepositoryComponent {
    fun getRepo(): ListUserRepository
}