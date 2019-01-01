package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread

import com.mroz.mateusz.mvvm_android_architecture_dagger2.AppExecutors
import com.mroz.mateusz.mvvm_android_architecture_dagger2.web_api.*
import timber.log.Timber


abstract class NetworkBoundResource<ResultType, RequestType>
    @MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init{
        result.value = Resource.loading(null)
        @Suppress("LeakingThis") //?
        val dbSource = lodFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)

            if(shouldFetch(data)) {
                fetchFromNetwork(dbSource)
                Timber.d("FROM_API")
            }

            else  {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                    Timber.d("FROM_DB")
                }
            }
        }
    }

    private fun setValue(newValue: Resource<ResultType>) {
        if(result.value != newValue) result.value = newValue
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) {newData ->
            setValue(Resource.loading(newData)!!)
        }

        result.addSource(apiResponse) {response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when(response) {
                is ApiSuccessResponse -> {
                    appExecutors.diskIO().execute {
                        saveCallResult(processResponse(response))
                        appExecutors.mainThread().execute {
                            result.addSource(lodFromDb()) {newData ->
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                }

                is ApiEmptyResponse -> {
                    appExecutors.mainThread().execute {
                        result.addSource(lodFromDb()) {newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }

                is ApiErrorResponse -> {
                    onFetchFailed()
                    result.addSource(dbSource) {newData ->
                        setValue(Resource.error(response.errorMessage, newData))
                        Timber.d(response.errorMessage)
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun lodFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

}