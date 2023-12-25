package com.vr.app.sh.domain.repository.local

import com.vr.app.sh.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepo {
    suspend fun insertBooks(listBooks:List<Book>)
    fun getBookInOneClass(numClass:Int): Flow<List<Book>>
}