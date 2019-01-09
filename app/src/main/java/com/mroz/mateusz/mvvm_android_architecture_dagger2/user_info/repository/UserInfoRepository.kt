package com.mroz.mateusz.mvvm_android_architecture_dagger2.user_info.repository

import android.arch.lifecycle.LiveData
import com.mroz.mateusz.mvvm_android_architecture_dagger2.AppExecutors
import com.mroz.mateusz.mvvm_android_architecture_dagger2.db.UserDao
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.Results
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository.NetworkBoundResource
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.ApiResponse
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.Resource


class UserInfoRepository constructor(
        private val appExecutors: AppExecutors,
        private val userDao:UserDao){

    fun loadUser(userId:Int): LiveData<Resource<Results>> {
        return object: NetworkBoundResource<Results, Results>(appExecutors) {
            override fun saveCallResult(item: Results) {}

            override fun createCall(): LiveData<ApiResponse<Results>>? = null

            override fun shouldFetch(data: Results?) = false

            override fun lodFromDb(): LiveData<Results> = userDao.getUser(userId)
        }.asLiveData()
    }
}