package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.Toast
import com.mroz.mateusz.mvvm_android_architecture_dagger2.R
import com.mroz.mateusz.mvvm_android_architecture_dagger2.databinding.ActivityMainBinding
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.DaggerRepositoryComponent
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module.RepoModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.User
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.recycler_view.ListUserAdapter
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel.ListProfileViewModel
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel.ListProfileViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    val TAG:String = this::class.java.simpleName

    @Inject
    lateinit var viewModelFactory:ListProfileViewModelFactory

    lateinit var viewModel: ListProfileViewModel
    lateinit var binding: ActivityMainBinding
    //temp
    lateinit var recyclerView: RecyclerView
    var adapterList:ListUserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        DaggerRepositoryComponent.builder()
                .repoModule(RepoModule(application))
                .build()
                .injectRepo(this)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)


        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListProfileViewModel::class.java)
        viewModel.users!!.observe(this, Observer<User> { users ->
            adapterList = ListUserAdapter(users!!.listUsers!!)
            recyclerView.adapter = adapterList
        })


    }
}
