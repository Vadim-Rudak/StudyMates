package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.domain.model.response.ListResponse
import com.vr.app.sh.domain.repository.internet.BookInternetRepo

class GetAllBookListInternet(private val bookInternetRepo: BookInternetRepo) {
    suspend fun execute(): ListResponse<Book> {
        return bookInternetRepo.getAllBookList()
    }
}