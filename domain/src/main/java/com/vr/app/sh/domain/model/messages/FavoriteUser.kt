package com.vr.app.sh.domain.model.messages

import com.vr.app.sh.domain.model.User

class FavoriteUser(){
    var id: Int? = null
    var userId: Int? = null
    var user: User? = null

    constructor(userId:Int) : this() {
        this.userId = userId
    }

    constructor(id: Int?, userId: Int?, user: User?) : this() {
        this.id = id
        this.userId = userId
        this.user = user
    }
}
