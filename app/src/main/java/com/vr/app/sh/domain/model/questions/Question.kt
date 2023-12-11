package com.vr.app.sh.domain.model.questions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Question(){

    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("testid")
    @Expose
    var test_id:Int = 0

    @SerializedName("type")
    @Expose
    var type: TypeQuestion? = TypeQuestion.Answers

    @SerializedName("question")
    @Expose
    var question: String? = null

    @SerializedName("correctAnswer")
    @Expose
    var correctAnswer: String? = null


    override fun toString(): String {
        return "Question(id=$id, test_id=$test_id, type=$type, question=$question, correctAnswer=$correctAnswer)"
    }


}

