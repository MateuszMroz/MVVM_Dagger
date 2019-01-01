package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository

import android.arch.lifecycle.LiveData
import com.mroz.mateusz.mvvm_android_architecture_dagger2.AppExecutors
import com.mroz.mateusz.mvvm_android_architecture_dagger2.db.UserDao
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.Results
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.User
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.*
import javax.inject.Inject


class ListUserRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val userDao: UserDao,
        private val randomUsersListApi: RandomUsersListApi) {

    fun lodUsers(count:Int): LiveData<Resource<List<Results>>> {
        return object: NetworkBoundResource<List<Results>, User>(appExecutors) {
            override fun saveCallResult(item: User) {
                userDao.insertListUser(item.listUsers!!)
            }

            override fun shouldFetch(data: List<Results>?) = data == null || data.isEmpty()

            override fun lodFromDb(): LiveData<List<Results>> = userDao.findUsers()

            override fun createCall(): LiveData<ApiResponse<User>> = randomUsersListApi.getRandomUsers(count)

        }.asLiveData()
    }
}