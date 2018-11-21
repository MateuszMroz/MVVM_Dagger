package com.mroz.mateusz.mvvm_android_architecture_dagger2

import android.app.Application
import timber.log.Timber


class MVVMDaggerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(object: Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return super.createStackElementTag(element) + ": " + element.lineNumber
            }
        })
    }
}