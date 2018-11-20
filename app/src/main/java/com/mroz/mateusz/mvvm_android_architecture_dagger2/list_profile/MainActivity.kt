package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mroz.mateusz.mvvm_android_architecture_dagger2.R
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.DaggerListUserComponent
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.ListUserComponent
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module.ContextModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.User
import com.mroz.mateusz.mvvm_android_architecture_dagger2.retrofit.RandomUsersListApi
import com.mroz.mateusz.mvvm_android_architecture_dagger2.retrofit.RequestCallback
import com.squareup.picasso.Picasso
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    lateinit var randomUsersListApi: RandomUsersListApi
    lateinit var picasso: Picasso
    lateinit var  requestCallback: RequestCallback<User>
    val TAG:String = this::class.java.simpleName

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
                Timber.d(TAG, response.listUsers!![0].email)
            }

            override fun onFailure(message: String, code: Int) {
                Timber.d(TAG, "ERROR")
            }
        }
    }
}
