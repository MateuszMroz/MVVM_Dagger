package com.mroz.mateusz.mvvm_android_architecture_dagger2

import android.app.Application
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.DaggerRepositoryComponent
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module.RepoModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository.ListUserRepository
import timber.log.Timber


class MVVMDaggerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(object: Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return super.createStackElementTag(element) + ": " + element.lineNumber
            }
        })

        //DaggerRepositoryComponent.create().injectRepo(ListUserRepository(this))
        DaggerRepositoryComponent.builder().repoModule(RepoModule(this)).build()

    }

}