package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.data.model.Book
import com.vr.app.sh.data.repository.DAOBook

class SaveBookListInBD(private val bookRepo: DAOBook) {
    suspend fun execute(list: List<Book>){
        bookRepo.insertBooks(list)
    }
}