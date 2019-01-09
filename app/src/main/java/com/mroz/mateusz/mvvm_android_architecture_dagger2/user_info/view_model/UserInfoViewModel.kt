package com.mroz.mateusz.mvvm_android_architecture_dagger2.user_info.view_model

import android.arch.lifecycle.LiveData
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.Results
import com.mroz.mateusz.mvvm_android_architecture_dagger2.user_info.repository.UserInfoRepository
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.Resource


class UserInfoViewModel constructor(private val userInfoRepository: UserInfoRepository) {
    val user: LiveData<Resource<Results>>? = null
}