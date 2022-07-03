package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.BookInternetRepo
import okhttp3.ResponseBody
import retrofit2.Call

class GetBookFile(private val bookInternetRepo: BookInternetRepo) {
    fun execute(id_book:Int): Call<ResponseBody> {
        return bookInternetRepo.getBookFile(id_book)
    }
}