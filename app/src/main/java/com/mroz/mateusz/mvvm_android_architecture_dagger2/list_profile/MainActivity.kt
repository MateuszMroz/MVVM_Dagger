package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mroz.mateusz.mvvm_android_architecture_dagger2.R
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.DaggerListUserComponent
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.ListUserComponent
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module.ContextModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.User
import com.mroz.mateusz.mvvm_android_architecture_dagger2.retrofit.RandomUsersListApi
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var randomUsersListApi: RandomUsersListApi
    lateinit var picasso: Picasso
    val TAG:String = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listUserComponent: ListUserComponent = DaggerListUserComponent.builder()
                .contextModule(ContextModule(this))
                .build()

        randomUsersListApi = listUserComponent.getRandomUsersService()
        picasso = listUserComponent.getPicasso()

        var randomUsersCall = randomUsersListApi.getRandomUsers(5)
        randomUsersCall.enqueue(object : retrofit2.Callback<User>{
            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                Log.d(TAG, response?.body()?.listUsers!![0].email)
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Log.d(TAG, "ERROR")
            }
        })

    }
}
