package com.vr.app.sh.domain.model.questions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class QuestionWithImg:Question() {
    @SerializedName("resImg")
    @Expose
    var resImg: String? = null

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
}