package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.module

import com.mroz.mateusz.mvvm_android_architecture_dagger2.AppExecutors
import com.mroz.mateusz.mvvm_android_architecture_dagger2.db.UserDao
import com.mroz.mateusz.mvvm_android_architecture_dagger2.db.dagger.UserDbModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository.ListUserRepository
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.viewModel.ListProfileViewModelFactory
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.RandomUsersListApi
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.dagger.module.RandomUsersListModule
import dagger.Module
import dagger.Provides

@Module(includes = [RandomUsersListModule::class, UserDbModule::class])
class RepoModule {

    @Provides
    fun appExecutors(): AppExecutors = AppExecutors()

    @Provides
    fun repo(appExecutors: AppExecutors,
             userDao: UserDao,
             randomUsersListApi:RandomUsersListApi): ListUserRepository = ListUserRepository(
                appExecutors = appExecutors,
                userDao = userDao,
                randomUsersListApi = randomUsersListApi)

    @Provides
    fun viewModelFactory(repository: ListUserRepository): ListProfileViewModelFactory = ListProfileViewModelFactory(repository)
}