package com.vr.app.sh.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Question {

    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("question")
    @Expose
    var question: String? = null

    @SerializedName("otv1")
    @Expose
    var answer1: String? = null

    @SerializedName("otv2")
    @Expose
    var answer2: String? = null

    @SerializedName("otv3")
    @Expose
    var answer3: String? = null

    @SerializedName("otv4")
    @Expose
    var answer4: String? = null

    @SerializedName("correctotv")
    @Expose
    var correctAnswer: String? = null

    @SerializedName("testid")
    @Expose
    var test_id: Int = 0

    constructor(
        id: Int,
        question: String?,
        otv1: String?,
        otv2: String?,
        otv3: String?,
        otv4: String?,
        correct_otv: String?,
        test_id: Int
    ) {
        this.id = id
        this.question = question
        this.answer1 = otv1
        this.answer2 = otv2
        this.answer3 = otv3
        this.answer4 = otv4
        this.correctAnswer = correct_otv
        this.test_id = test_id
    }

    constructor(
        id: Int,
        question: String?,
        otv1: String?,
        otv2: String?,
        otv3: String?,
        otv4: String?,
        correct_otv: String?
    ) {
        this.id = id
        this.question = question
        this.answer1 = otv1
        this.answer2 = otv2
        this.answer3 = otv3
        this.answer4 = otv4
        this.correctAnswer = correct_otv
    }


}