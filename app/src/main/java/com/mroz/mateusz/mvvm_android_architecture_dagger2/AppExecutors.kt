package com.mroz.mateusz.mvvm_android_architecture_dagger2

import java.util.concurrent.Executor
import java.util.concurrent.Executors

import android.os.Handler
import android.os.Looper
import javax.inject.Inject

class AppExecutors(
        private val diskIO: Executor,
        private val networkIO: Executor,
        private val mainThread: Executor) {

    @Inject
    constructor(): this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(3),
        MainThreadExecutor()
    )

    fun diskIO():Executor = diskIO

    fun networkIO(): Executor = networkIO

    fun mainThread(): Executor = mainThread

    private class MainThreadExecutor: Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable?) {
            mainThreadHandler.post(command)
        }
    }
}