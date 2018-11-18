package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module

import android.content.Context
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.scope.RandomUserApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ContextModule(private val context: Context) {
    @Named("application_context")
    @RandomUserApplicationScope
    @Provides
    fun getContext(): Context = context.applicationContext
}