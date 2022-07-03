package com.vr.app.sh.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Question {

    constructor(
        id: Int,
        question: String?,
        otv1: String?,
        otv2: String?,
        otv3: String?,
        otv4: String?,
        correctOtv: String?,
        testid: Int
    ) {
        this.id = id
        this.question = question
        this.otv1 = otv1
        this.otv2 = otv2
        this.otv3 = otv3
        this.otv4 = otv4
        this.correctOtv = correctOtv
        this.testid = testid
    }

    constructor(
        question: String,
        otv1: String,
        otv2: String,
        otv3: String,
        otv4: String,
        correctOtv: String
    ) {
        this.question = question
        this.otv1 = otv1
        this.otv2 = otv2
        this.otv3 = otv3
        this.otv4 = otv4
        this.correctOtv = correctOtv
    }


    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("question")
    @Expose
    var question: String? = null

    @SerializedName("otv1")
    @Expose
    var otv1: String? = null

    @SerializedName("otv2")
    @Expose
    var otv2: String? = null

    @SerializedName("otv3")
    @Expose
    var otv3: String? = null

    @SerializedName("otv4")
    @Expose
    var otv4: String? = null

    @SerializedName("correctotv")
    @Expose
    var correctOtv: String? = null

    @SerializedName("testid")
    @Expose
    var testid = 0
}