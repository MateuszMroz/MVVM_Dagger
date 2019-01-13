package com.mroz.mateusz.mvvm_android_architecture_dagger2.user_info

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mroz.mateusz.mvvm_android_architecture_dagger2.R
import com.mroz.mateusz.mvvm_android_architecture_dagger2.databinding.ActivityUserInfoBinding

class UserInfoActivity : AppCompatActivity() {

    lateinit var binding:ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)
    }
}
