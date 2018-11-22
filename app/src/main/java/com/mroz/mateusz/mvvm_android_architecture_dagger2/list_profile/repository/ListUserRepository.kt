package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository

import android.app.Application
import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.context.ContextModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.DaggerListUserComponent
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.ListUserComponent
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.User
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.CallBackKt
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.RandomUsersListApi
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.RequestCallback
import com.squareup.picasso.Picasso
import timber.log.Timber


class ListUserRepository(application:Application) {
    val TAG:String = this::class.java.simpleName

    var randomUsersListApi: RandomUsersListApi
    var picasso: Picasso
    var listUserComponent: ListUserComponent

    lateinit var requestCallback: RequestCallback<User>

    init {
        listUserComponent = DaggerListUserComponent.builder()
                .contextModule(ContextModule(application.applicationContext))
                .build()

        randomUsersListApi = listUserComponent.getRandomUsersService()
        picasso = listUserComponent.getPicasso()
    }


    fun getListUserFromWebApi(countUser:Int) {
        requestCallback = object: RequestCallback<User> {
            override fun onSuccess(response: User) {
                Timber.w(TAG, response.listUsers!![0].email)
            }

            override fun onFailure(message: String, code: Int) {
                Timber.w(TAG, message)
            }
        }

        randomUsersListApi.getRandomUsers(countUser).enqueue(CallBackKt<User>(requestCallback))
    }



}