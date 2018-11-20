package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.graphics.Picture




class Results {
    @SerializedName("gender")
    @Expose
    val gender: String = ""
    @SerializedName("name")
    @Expose
    private val name: Name? = null
    @SerializedName("location")
    @Expose
    private val location: Location? = null
    @SerializedName("email")
    @Expose
    val email: String = ""
    @SerializedName("login")
    @Expose
    private val login: Login? = null
    @SerializedName("dob")
    @Expose
    val dateOfBirthday: DateOfBirthday? = null
    @SerializedName("registered")
    @Expose
    val registered: String = ""
    @SerializedName("phone")
    @Expose
    val phone: String = ""
    @SerializedName("cell")
    @Expose
    val cell: String? = null
    @SerializedName("picture")
    @Expose
    val picture: Picture? = null
}