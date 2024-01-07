package com.vr.app.sh.domain.model.response

class DownloadFile() {

    var success: Boolean? = null
    var progress:Long = 0
    var infoToSend:String = ""

    constructor(success: Boolean) : this() {
        this.success = success
    }

    constructor(success: Boolean, infoToSend: String) : this() {
        this.success = success
        this.infoToSend = infoToSend
    }

    constructor(success: Boolean?, progress: Long) : this() {
        this.success = success
        this.progress = progress
    }
}