package com.vr.app.sh.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Auth {

    constructor(name_user: String?, message: String?, status: Boolean?, role: String?) {
        this.name_user = name_user
        this.message = message
        this.status = status
        this.role = role
    }

    @SerializedName("name_user")
    @Expose
    var name_user: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("status")
    @Expose
    var status:Boolean? = false

    @SerializedName("role")
    @Expose
    var role: String? = ""
}