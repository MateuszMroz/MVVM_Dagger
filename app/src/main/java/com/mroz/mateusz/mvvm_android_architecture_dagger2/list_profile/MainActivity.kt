package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.mroz.mateusz.mvvm_android_architecture_dagger2.AppExecutors
import com.mroz.mateusz.mvvm_android_architecture_dagger2.R
import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.context.ContextModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.databinding.ActivityMainBinding
import com.mroz.mateusz.mvvm_android_architecture_dagger2.db.dagger.UserDbModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.DaggerRepositoryComponent
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module.AdapterModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module.RepoModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.Results
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.recycler_view.ListUserAdapter
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel.ListProfileViewModel
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel.ListProfileViewModelFactory
import com.mroz.mateusz.mvvm_android_architecture_dagger2.utils.RetryCallback
import timber.log.Timber
import javax.inject.Inject


class MainActivity : AppCompatActivity(), ListUserAdapter.ClickListener {

    val TAG:String = this::class.java.simpleName

    lateinit var binding: ActivityMainBinding

    @Inject lateinit var viewModelFactory:ListProfileViewModelFactory
    lateinit var viewModel: ListProfileViewModel

    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject lateinit var adapterList:ListUserAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        DaggerRepositoryComponent.builder()
                .repoModule(RepoModule())
                .contextModule(ContextModule(application))
                .userDbModule(UserDbModule(application))
                .adapterModule(AdapterModule(this))
                .build()
                .injectRepo(this)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterList

        binding.retryCallback = object: RetryCallback {
            override fun retry() {
                onRefresh()
            }

        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListProfileViewModel::class.java)

        binding.users = viewModel.mediatorLiveData
        binding.setLifecycleOwner(this)

        onRefresh()
        initUserList()
    }

    private fun initUserList() {
        viewModel.mediatorLiveData.observe(this, Observer { user ->
            try {
                adapterList.setListUsers(user?.data!!)
            }catch(e:KotlinNullPointerException ) {
                Timber.d("NullPointerException")
            }
        })
    }

    private fun onRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun onClick(user: Results) {
        Toast.makeText(this, user.email, Toast.LENGTH_LONG).show()
    }
}
