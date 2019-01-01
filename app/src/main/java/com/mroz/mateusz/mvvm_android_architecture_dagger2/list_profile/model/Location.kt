package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.model

import com.google.gson.annotations.Expose

class Location {
    @Expose
    var street: String = ""
    @Expose
    var city:String = ""
    @Expose
    var state: String = ""
    @Expose
    var postcode:String = ""
}