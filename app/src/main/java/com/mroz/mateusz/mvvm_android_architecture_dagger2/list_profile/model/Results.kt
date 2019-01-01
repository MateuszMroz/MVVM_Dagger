package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model

import android.arch.persistence.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "results")
class Results {
    @PrimaryKey(autoGenerate = true)
    @Expose(serialize = false, deserialize = false)
    var _id: Int = 0

    @Expose
    @SerializedName("gender")
    var gender: String = ""

    @Expose
    @SerializedName("name")
    @Embedded(prefix = "name_")
    var name: Name? = null

    @Expose
    @SerializedName("location")
    @Embedded(prefix = "location_")
    var location: Location? = null

    @Expose
    @SerializedName("email")
    var email: String = ""

    @Expose
    @SerializedName("login")
    @Embedded(prefix = "login_")
    var login: Login? = null

    @Expose
    @SerializedName("dob")
    @Embedded(prefix = "dob_")
    var dateOfBirthday: DateOfBirthday? = null

    @Expose
    @SerializedName("phone")
    var phone: String = ""

    @Expose
    @SerializedName("cell")
    var cell: String = ""

    @Expose
    @SerializedName("picture")
    @Embedded(prefix = "picture_")
    var pictureJSON: PictureJSON? = null
}