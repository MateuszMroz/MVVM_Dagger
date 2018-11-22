package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.User
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository.ListUserRepository
import javax.inject.Inject


class ListProfileViewModel(userRepository: ListUserRepository) : ViewModel() {
    var users: LiveData<User>? = null

    init {
        users = userRepository?.getListUserFromWebApi(5)
    }
}