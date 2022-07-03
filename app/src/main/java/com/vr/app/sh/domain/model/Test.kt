package com.vr.app.sh.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Test {

    constructor(id: Int, subject: String?, numclass: Int, nametest: String?, numquestions: Int) {
        this.id = id
        this.subject = subject
        this.numclass = numclass
        this.nametest = nametest
        this.numquestions = numquestions
    }

    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("subject")
    @Expose
    var subject: String? = null

    @SerializedName("numclass")
    @Expose
    var numclass = 0

    @SerializedName("nametest")
    @Expose
    var nametest: String? = null

    @SerializedName("numquestions")
    @Expose
    var numquestions = 0
}