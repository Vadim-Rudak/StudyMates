package com.vr.app.sh.domain.model

class Lesson(){
    var id: Int = 0
    var num_day: Int = 0
    var name: String? = null
    var timeStart: String? = null
    var timeEnd: String? = null
    var num_class: String? = null

    constructor(
        id: Int,
        num_day: Int,
        name: String?,
        timeStart: String?,
        timeEnd: String?,
        num_class: String?
    ) : this() {
        this.id = id
        this.num_day = num_day
        this.name = name
        this.timeStart = timeStart
        this.timeEnd = timeEnd
        this.num_class = num_class
    }
}