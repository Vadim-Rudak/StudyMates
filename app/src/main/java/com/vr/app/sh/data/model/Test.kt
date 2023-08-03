package com.vr.app.sh.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Tests")
data class Test(

    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true) var id: Int,

    @SerializedName("subject")
    @Expose
    @ColumnInfo(name = "subject") var subject:String? = null,

    @SerializedName("numclass")
    @Expose
    @ColumnInfo(name = "numclass") var num_class:Int = 0,

    @SerializedName("nametest")
    @Expose
    @ColumnInfo(name = "nametest") var name_test: String? = null,

    @SerializedName("numquestions")
    @Expose
    @ColumnInfo(name = "numquestions") var num_questions:Int = 0
)
