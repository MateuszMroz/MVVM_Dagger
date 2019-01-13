package com.mroz.mateusz.mvvm_android_architecture_dagger2

import android.app.Application
import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.ViewModelModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.context.ContextModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.db.dagger.UserDbModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.DaggerRepositoryComponent
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.RepositoryComponent
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module.RepoModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.utils.ReleaseTree
import timber.log.Timber


class MVVMDaggerApplication : Application() {

    lateinit var repositoryComponent: RepositoryComponent

    override fun onCreate() {
        super.onCreate()

        repositoryComponent = DaggerRepositoryComponent.builder()
                .repoModule(RepoModule())
                .contextModule(ContextModule(this))
                .userDbModule(UserDbModule(this))
                .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(object: Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + ": " + element.lineNumber
                }
            })
        } else {
            Timber.plant(ReleaseTree())
        }

    }

    fun getRepoComponent() = repositoryComponent

}