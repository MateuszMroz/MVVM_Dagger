package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.graphics.Picture




class Result {
    @SerializedName("gender")
    @Expose
    val gender: String? = null
   /* @SerializedName("name")
    @Expose
    private val name: Name? = null*/
    /*@SerializedName("location")
    @Expose
    private val location: Location? = null*/
    @SerializedName("email")
    @Expose
    val email: String? = null
    /*@SerializedName("login")
    @Expose
    private val login: Login? = null*/
    @SerializedName("dob")
    @Expose
    val dob: String? = null
    @SerializedName("registered")
    @Expose
    val registered: String? = null
    @SerializedName("phone")
    @Expose
    val phone: String? = null
    @SerializedName("cell")
    @Expose
    val cell: String? = null
    /*@SerializedName("id")
    @Expose
    private val id: Id? = null*/
    @SerializedName("picture")
    @Expose
    val picture: Picture? = null
    @SerializedName("nat")
    @Expose
    val nat: String? = null
}