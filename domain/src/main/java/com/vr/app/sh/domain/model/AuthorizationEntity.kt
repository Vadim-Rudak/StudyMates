package com.vr.app.sh.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AuthorizationEntity {

    constructor(status: Boolean?, message: String?, user: User?) {
        this.status = status
        this.message = message
        this.user = user
    }

    @SerializedName("status")
    @Expose
    var status:Boolean? = false

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("userMod")
    @Expose
    var user: User? = null

    override fun toString(): String {
        return "Auth(status=$status, message=$message, user=$user)"
    }

}