package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module

import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.recycler_view.ListUserAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule(var clickListener: ListUserAdapter.ClickListener) {

    @Provides
    fun listener(): ListUserAdapter.ClickListener = clickListener

    @Provides
    fun adapter(listener: ListUserAdapter.ClickListener): ListUserAdapter = ListUserAdapter(listener)

}