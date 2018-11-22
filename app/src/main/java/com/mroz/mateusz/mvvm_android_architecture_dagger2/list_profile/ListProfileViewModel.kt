package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.User
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository.ListUserRepository
import javax.inject.Inject


class ListProfileViewModel : ViewModel() {
    var users: LiveData<User>? = null
    /*@Inject
    lateinit var userRepo: ListUserRepository

    init {
        users = userRepo?.getListUserFromWebApi(5)
    }*/
}