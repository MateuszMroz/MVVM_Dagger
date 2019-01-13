package com.mroz.mateusz.mvvm_android_architecture_dagger2.user_info.view_model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.Results
import com.mroz.mateusz.mvvm_android_architecture_dagger2.user_info.repository.UserInfoRepository
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.Resource
import javax.inject.Inject


class UserInfoViewModel @Inject constructor(private val userInfoRepository: UserInfoRepository) : ViewModel()  {
    val user: LiveData<Resource<Results>>? = null
}