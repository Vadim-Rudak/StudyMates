package com.vr.app.sh.domain.model

class WriteToFile() {

    var status: Boolean? = null
    var progress:Long = 0

    constructor(status: Boolean, progress: Long) : this() {
        this.status = status
        this.progress = progress
    }

    constructor(status: Boolean?) : this() {
        this.status = status
    }
}