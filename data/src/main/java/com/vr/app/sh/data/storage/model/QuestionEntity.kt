package com.vr.app.sh.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vr.app.sh.domain.model.Question

@Entity(tableName = "Questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "question") var question: String? = null,
    @ColumnInfo(name = "otv1") var otv1: String? = null,
    @ColumnInfo(name = "otv2") var otv2: String? = null,
    @ColumnInfo(name = "otv3") var otv3: String? = null,
    @ColumnInfo(name = "otv4") var otv4: String? = null,
    @ColumnInfo(name = "correctOtv") var correct_otv: String? = null,
    @ColumnInfo(name = "testid") var test_id:Int = 0

){
    fun toQuestion() = Question(id, question, otv1, otv2, otv3, otv4, correct_otv, test_id)
}