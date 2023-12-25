package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.domain.repository.local.BookRepo

class SaveBookListInBD(private val bookRepo: BookRepo) {
    suspend fun execute(list: List<Book>){
        bookRepo.insertBooks(list)
    }
}