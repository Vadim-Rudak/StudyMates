package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.domain.repository.local.BookRepo
import kotlinx.coroutines.flow.Flow

class GetListBookInClass(private val bookRepo: BookRepo) {
    fun execute(numClass:Int): Flow<List<Book>> {
        return bookRepo.getBookInOneClass(numClass)
    }
}