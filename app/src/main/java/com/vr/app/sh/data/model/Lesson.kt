package com.vr.app.sh.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "Lessons")
data class Lesson(

    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "numday") var num_day: Int = 0,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "timestart") var timeStart: String? = null,
    @ColumnInfo(name = "timeend") var timeEnd: String? = null,
    @ColumnInfo(name = "numclass") var num_class: String? = null

)