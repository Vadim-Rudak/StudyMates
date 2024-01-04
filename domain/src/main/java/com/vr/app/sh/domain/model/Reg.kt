package com.vr.app.sh.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vr.app.sh.domain.model.User

class Reg() {

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("status_reg")
    @Expose
    var statusReg:Boolean? = false

    @SerializedName("user")
    @Expose
    var user: User? = null

    constructor(message: String?, statusReg: Boolean?):this() {
        this.message = message
        this.statusReg = statusReg
    }

    constructor(statusReg: Boolean?):this(){
        this.statusReg = statusReg
    }
}