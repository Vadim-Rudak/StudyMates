package com.vr.app.sh.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Books")
data class Book(

    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true) var id: Int,

    @SerializedName("numclass")
    @Expose
    @ColumnInfo(name = "numclass") var num_class:Int,

    @SerializedName("namebook")
    @Expose
    @ColumnInfo(name = "namebook") var name:String? = null,

    @SerializedName("imagebook")
    @Expose
    @ColumnInfo(name = "imagebook") var image:String? = null

)