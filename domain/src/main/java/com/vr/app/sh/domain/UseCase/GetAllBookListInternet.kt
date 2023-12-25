package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.domain.repository.internet.BookInternetRepo
import retrofit2.Response

class GetAllBookListInternet(private val bookInternetRepo: BookInternetRepo) {
    suspend fun execute(): Response<List<Book>> {
        return bookInternetRepo.getAllBookList()
    }
}