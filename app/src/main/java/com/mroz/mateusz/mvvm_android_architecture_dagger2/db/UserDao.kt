package com.mroz.mateusz.mvvm_android_architecture_dagger2.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model.Results

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: Results)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListUser(listUser: List<Results>)

    @Query("SELECT * FROM results")
    fun findUsers():LiveData<List<Results>>

    @Query("SELECT * FROM results WHERE _id = :userId" )
    fun getUser(userId:Int): LiveData<Results>

    @Delete
    fun deleteUser(user: Results)

    @Query("DELETE FROM results")
    fun deleteAllUsers()

}