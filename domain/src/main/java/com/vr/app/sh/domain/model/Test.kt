package com.vr.app.sh.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Test() {

    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("subject")
    @Expose
    var subject: String? = null

    @SerializedName("numclass")
    @Expose
    var num_class: Int = 0

    @SerializedName("nametest")
    @Expose
    var name_test: String? = null

    @SerializedName("numquestions")
    @Expose
    var num_questions: Int = 0

    constructor(id: Int, subject: String?, num_class: Int, name_test: String?, num_questions: Int) : this() {
        this.id = id
        this.subject = subject
        this.num_class = num_class
        this.name_test = name_test
        this.num_questions = num_questions
    }
}
