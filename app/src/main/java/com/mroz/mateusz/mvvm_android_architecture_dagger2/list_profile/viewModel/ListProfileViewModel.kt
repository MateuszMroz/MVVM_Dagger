package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.Results
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository.ListUserRepository
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.Resource
import timber.log.Timber
import javax.inject.Inject


class ListProfileViewModel @Inject constructor(private val userRepository: ListUserRepository) : ViewModel() {

    val mediatorLiveData:MediatorLiveData<Resource<List<Results>>> = MediatorLiveData()
    var users: LiveData<Resource<List<Results>>> =  userRepository.lodUsers(5)

    init {
        mediatorLiveData.addSource(users) {
            mediatorLiveData.value = it
        }
    }

    fun refresh() {
        mediatorLiveData.removeSource(users)
        users = userRepository.lodUsers(5)
        mediatorLiveData.addSource(users){
            mediatorLiveData.value = it
        }
        Timber.d("REFRESH")
    }
}