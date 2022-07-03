package com.vr.app.sh.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Reg {

    constructor(message: String?, status_reg: Boolean?) {
        this.message = message
        this.status_reg = status_reg
    }

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("status_reg")
    @Expose
    var status_reg:Boolean? = false

}