package com.mroz.mateusz.mvvm_android_architecture_dagger2.db.dagger

import android.app.Application
import android.arch.persistence.room.Room
import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.scope.RandomUserApplicationScope
import com.mroz.mateusz.mvvm_android_architecture_dagger2.db.UserDao
import com.mroz.mateusz.mvvm_android_architecture_dagger2.db.UserDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class UserDbModule(val app:Application) {
    @RandomUserApplicationScope
    @Provides
    fun provideDb(): UserDb {
        return Room
                .databaseBuilder(app, UserDb::class.java, "list_user.db")
                .fallbackToDestructiveMigration()
                .build()
    }
    @RandomUserApplicationScope
    @Provides
    fun provideUserDao(db: UserDb): UserDao {
        return db.userDao()
    }
}