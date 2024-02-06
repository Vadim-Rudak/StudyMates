package com.vr.app.sh.domain.model.messages

class Chat() {

    var id:Int = 0
    var name:String?=null
    var userCreate:Int?=null

    constructor(id: Int, name: String?, userCreate: Int?) : this() {
        this.id = id
        this.name = name
        this.userCreate = userCreate
    }
}