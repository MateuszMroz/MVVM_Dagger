package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "picture_json")
class PictureJSON {
    var large:String = ""
    var medium:String = ""
    var thumbnail:String = ""
}