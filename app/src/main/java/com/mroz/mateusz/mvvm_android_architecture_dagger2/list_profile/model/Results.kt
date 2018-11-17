package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.graphics.Picture




class Results {
    @SerializedName("gender")
    @Expose
    val gender: String? = null
    @SerializedName("email")
    @Expose
    val email: String? = null
    @SerializedName("phone")
    @Expose
    val phone: String? = null
    @SerializedName("cell")
    @Expose
    val cell: String? = null
    @SerializedName("nat")
    @Expose
    val nat: String? = null
}