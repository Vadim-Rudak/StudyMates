package com.vr.app.sh.domain.model.response

class ListResponse<T> {

    var success:Boolean = false
    var list:List<T>? = null

    constructor()
    constructor(success: Boolean, list: List<T>?) {
        this.success = success
        this.list = list
    }
}