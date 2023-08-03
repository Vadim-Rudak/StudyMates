package com.vr.app.sh.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Questions")
data class Question(

    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true) var id: Int,

    @SerializedName("question")
    @Expose
    @ColumnInfo(name = "question") var question: String? = null,

    @SerializedName("otv1")
    @Expose
    @ColumnInfo(name = "otv1") var otv1: String? = null,

    @SerializedName("otv2")
    @Expose
    @ColumnInfo(name = "otv2") var otv2: String? = null,

    @SerializedName("otv3")
    @Expose
    @ColumnInfo(name = "otv3") var otv3: String? = null,

    @SerializedName("otv4")
    @Expose
    @ColumnInfo(name = "otv4") var otv4: String? = null,

    @SerializedName("correctotv")
    @Expose
    @ColumnInfo(name = "correctOtv") var correct_otv: String? = null,

    @SerializedName("testid")
    @Expose
    @ColumnInfo(name = "testid") var test_id:Int = 0

)