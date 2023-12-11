package com.vr.app.sh.domain.model.questions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class QuestionWithAnswers: Question() {
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


    override fun toString(): String {
        return "QuestionWithAnswers(otv1=$otv1, otv2=$otv2, otv3=$otv3, otv4=$otv4, question = $question)"
    }


}