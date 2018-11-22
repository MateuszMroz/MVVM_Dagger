package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository.ListUserRepository
import javax.inject.Inject


class ListProfileViewModelFactory
    @Inject constructor(var repository: ListUserRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ListProfileViewModel::class.java!!)) {
            ListProfileViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}