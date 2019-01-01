package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.Results
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository.ListUserRepository
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.Resource
import javax.inject.Inject


class ListProfileViewModel @Inject constructor(userRepository: ListUserRepository) : ViewModel() {

    val users: LiveData<Resource<List<Results>>> =  userRepository.lodUsers(5)
}