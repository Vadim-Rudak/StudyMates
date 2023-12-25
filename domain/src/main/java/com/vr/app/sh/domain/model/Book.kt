package com.vr.app.sh.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Book() {

    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("numclass")
    @Expose
    var num_class: Int = 0

    @SerializedName("namebook")
    @Expose
    var name: String? = null

    @SerializedName("imagebook")
    @Expose
    var image: String? = null
    constructor(id: Int, num_class: Int, name: String?, image: String?) : this() {
        this.id = id
        this.num_class = num_class
        this.name = name
        this.image = image
    }
}