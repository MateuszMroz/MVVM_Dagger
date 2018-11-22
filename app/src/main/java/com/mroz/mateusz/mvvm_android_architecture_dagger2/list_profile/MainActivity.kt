package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mroz.mateusz.mvvm_android_architecture_dagger2.R
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.DaggerRepositoryComponent
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module.RepoModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.User
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel.ListProfileViewModel
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel.ListProfileViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    val TAG:String = this::class.java.simpleName
    @Inject
    lateinit var viewModelFactory:ListProfileViewModelFactory

    lateinit var viewModel: ListProfileViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerRepositoryComponent.builder()
                .repoModule(RepoModule(application))
                .build()
                .injectRepo(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListProfileViewModel::class.java)
        viewModel.users!!.observe(this, Observer<User> { users ->
            Toast.makeText(this, users?.listUsers!![0].email, Toast.LENGTH_LONG).show()
        })

    }
}
