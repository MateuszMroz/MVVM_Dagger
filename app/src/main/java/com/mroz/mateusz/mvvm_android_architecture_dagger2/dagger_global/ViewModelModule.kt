package com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel.ListProfileViewModel
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel.ListProfileViewModelFactory
import com.mroz.mateusz.mvvm_android_architecture_dagger2.user_info.view_model.UserInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListProfileViewModel::class)
    abstract fun bindListUser(listUserViewModel: ListProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserInfoViewModel::class)
    abstract fun bindUserInfo(userInfoViewModel: UserInfoViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ListProfileViewModelFactory): ViewModelProvider.Factory
}