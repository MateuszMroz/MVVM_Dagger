package com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.context

import android.content.Context
import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.scope.RandomUserApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ActivityModule(var context: Context) {
    @Named("activity_context")
    @RandomUserApplicationScope
    @Provides
    fun context(): Context = this.context
}