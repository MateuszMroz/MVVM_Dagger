package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {

    @Provides
    fun getContext(): Context = context.applicationContext
}