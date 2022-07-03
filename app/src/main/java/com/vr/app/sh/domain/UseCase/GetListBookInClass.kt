package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.domain.repository.BookRepo

class GetListBookInClass(private val bookRepo: BookRepo) {
    fun execute(num_class:Int): ArrayList<Book> {
        return bookRepo.getBookInOneClass(num_class)
    }
}