package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.mroz.mateusz.mvvm_android_architecture_dagger2.AppExecutors
import com.mroz.mateusz.mvvm_android_architecture_dagger2.MVVMDaggerApplication
import com.mroz.mateusz.mvvm_android_architecture_dagger2.R
import com.mroz.mateusz.mvvm_android_architecture_dagger2.databinding.ActivityMainBinding
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.Results
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.recycler_view.ListUserAdapter
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel.ListProfileViewModel
import com.mroz.mateusz.mvvm_android_architecture_dagger2.utils.RetryCallback
import timber.log.Timber
import javax.inject.Inject


class MainActivity : AppCompatActivity(), ListUserAdapter.ClickListener {

    val TAG:String = this::class.java.simpleName

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: ListProfileViewModel

    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var adapterList:ListUserAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        adapterList = ListUserAdapter(this)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterList

        binding.retryCallback = object: RetryCallback {
            override fun retry() {
                onRefresh()
            }
        }


        var app: MVVMDaggerApplication = application as MVVMDaggerApplication
        app.getRepoComponent().injectRepo(this)


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
