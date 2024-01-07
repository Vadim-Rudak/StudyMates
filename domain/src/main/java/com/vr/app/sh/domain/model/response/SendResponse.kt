package com.vr.app.sh.domain.model.response

class SendResponse() {

    var success:Boolean = false
    var infoToSend:String = ""

    constructor(success: Boolean) : this() {
        this.success = success
    }

    constructor(success: Boolean, infoToSend: String) : this() {
        this.success = success
        this.infoToSend = infoToSend
    }
}