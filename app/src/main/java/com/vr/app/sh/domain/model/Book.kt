package com.vr.app.sh.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Book {

    constructor(id: Int, numclass: Int, namebook: String, imagebook: String) {
        this.id = id
        this.numclass = numclass
        this.namebook = namebook
        this.imagebook = imagebook
    }

    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("numclass")
    @Expose
    var numclass = 0

    @SerializedName("namebook")
    @Expose
    var namebook: String? = null

    @SerializedName("imagebook")
    @Expose
    var imagebook: String


}