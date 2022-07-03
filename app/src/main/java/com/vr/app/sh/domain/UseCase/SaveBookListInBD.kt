package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.domain.repository.BookRepo

class SaveBookListInBD(private val bookRepo: BookRepo) {
    fun execute(list: List<Book>){
        bookRepo.saveBooksInDB(list)
    }
}