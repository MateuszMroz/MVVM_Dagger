package com.mroz.mateusz.mvvm_android_architecture_dagger2.user_info

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mroz.mateusz.mvvm_android_architecture_dagger2.MVVMDaggerApplication
import com.mroz.mateusz.mvvm_android_architecture_dagger2.R
import com.mroz.mateusz.mvvm_android_architecture_dagger2.databinding.ActivityUserInfoBinding
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.Results
import com.mroz.mateusz.mvvm_android_architecture_dagger2.user_info.view_model.UserInfoViewModel
import com.mroz.mateusz.mvvm_android_architecture_dagger2.utils.USER_ID
import timber.log.Timber
import javax.inject.Inject

class UserInfoActivity : AppCompatActivity() {

    lateinit var binding:ActivityUserInfoBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: UserInfoViewModel

    var id:Int = 0
    lateinit var extra:Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)
        val app: MVVMDaggerApplication = application as MVVMDaggerApplication
        app.getRepoComponent().injectRepo(this)
        extra = intent.extras
        id = extra.getInt(USER_ID)

        binding.setLifecycleOwner(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserInfoViewModel::class.java)
        Timber.d(viewModel._user.value?.pictureJSON?.large)
        var user: LiveData<Results> = viewModel._user
        binding.user = user
    }
}
