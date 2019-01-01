package com.mroz.mateusz.mvvm_android_architecture_dagger2.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.Results

@Database(entities = [Results::class],
        version = 7,
        exportSchema = false)
abstract class UserDb : RoomDatabase() {
    abstract fun userDao(): UserDao
}