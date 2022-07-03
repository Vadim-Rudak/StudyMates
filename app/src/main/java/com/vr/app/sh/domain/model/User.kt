package com.vr.app.sh.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {

    constructor(name_user: String?, role: String?) {
        this.name_user = name_user
        this.role = role
    }

    constructor()

    var name_user: String? = null
    var role: String? = null
}