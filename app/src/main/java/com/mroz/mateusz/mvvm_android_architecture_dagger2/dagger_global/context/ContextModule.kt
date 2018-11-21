package com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.context

import android.content.Context
import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.scope.RandomUserApplicationScope
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