package com.vr.app.sh.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vr.app.sh.domain.model.Test

@Entity(tableName = "Tests")
data class TestEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "subject") var subject:String? = null,
    @ColumnInfo(name = "numclass") var num_class:Int = 0,
    @ColumnInfo(name = "nametest") var name_test: String? = null,
    @ColumnInfo(name = "numquestions") var num_questions:Int = 0
){
    fun toTest() = Test(id, subject, num_class, name_test, num_questions)
}