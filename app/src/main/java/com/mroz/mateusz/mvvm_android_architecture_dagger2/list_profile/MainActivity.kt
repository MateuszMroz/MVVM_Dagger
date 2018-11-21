package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mroz.mateusz.mvvm_android_architecture_dagger2.R
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.DaggerListUserComponent
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.ListUserComponent
import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.context.ContextModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.User
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.CallBackKt
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.RandomUsersListApi
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.RequestCallback
import com.squareup.picasso.Picasso
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    val TAG:String = this::class.java.simpleName

    lateinit var randomUsersListApi: RandomUsersListApi
    lateinit var picasso: Picasso
    lateinit var requestCallback: RequestCallback<User>
    lateinit var callbackKt: CallBackKt<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listUserComponent: ListUserComponent = DaggerListUserComponent.builder()
                .contextModule(ContextModule(this))
                .build()

        randomUsersListApi = listUserComponent.getRandomUsersService()
        picasso = listUserComponent.getPicasso()

        requestCallback = object: RequestCallback<User> {
            override fun onSuccess(response: User) {
                Timber.w(TAG, response.listUsers!![0].email)
            }

            override fun onFailure(message: String, code: Int) {
                Timber.w(TAG, message)
            }
        }

        callbackKt = CallBackKt(requestCallback)

        //tutaj tylko requestCallback, reszta ma być gotowa
        randomUsersListApi.getRandomUsers(5).enqueue(callbackKt)
    }
}
