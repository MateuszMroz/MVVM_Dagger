package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.User
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository.ListUserRepository


class ListProfileViewModel(var userRepository: ListUserRepository) : ViewModel() {
    var users: LiveData<User>? = null

    init {
        users = userRepository.getListUserFromWebApi(5)
    }

    fun refreshData(){
        users = userRepository.getListUserFromWebApi(5)
    }
}