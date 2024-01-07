package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.internet.BookInternetRepo
import java.io.File

class SendBook(private val bookInternetRepo: BookInternetRepo) {
    suspend fun execute(numClass: Int, nameBook: String, bookFile: File): Boolean {
        return bookInternetRepo.addBook(numClass, nameBook, bookFile)
    }
}