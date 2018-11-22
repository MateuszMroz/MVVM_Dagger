package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mroz.mateusz.mvvm_android_architecture_dagger2.R
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository.ListUserRepository


class MainActivity : AppCompatActivity() {
    val TAG:String = this::class.java.simpleName

    lateinit var viewModel: ListProfileViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var repository: ListUserRepository

        /*viewModel = ViewModelProviders.of(this).get(ListProfileViewModel::class.java)
        viewModel.users!!.observe(this, Observer<User> { users ->
            Toast.makeText(this, users?.listUsers!![0].email, Toast.LENGTH_LONG).show()
        })*/

    }
}
