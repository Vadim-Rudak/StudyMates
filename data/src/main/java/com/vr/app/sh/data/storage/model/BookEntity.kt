package com.vr.app.sh.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vr.app.sh.domain.model.Book

@Entity(tableName = "Books")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "numclass") var num_class:Int,
    @ColumnInfo(name = "namebook") var name:String? = null,
    @ColumnInfo(name = "imagebook") var image:String? = null
){
    fun toBook() = Book(id,num_class,name,image)
}