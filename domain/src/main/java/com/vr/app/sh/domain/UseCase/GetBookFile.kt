package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.internet.BookInternetRepo
import okhttp3.ResponseBody
import retrofit2.Call

class GetBookFile(private val bookInternetRepo: BookInternetRepo) {
    fun execute(idBook:Int): Call<ResponseBody> {
        return bookInternetRepo.getBookFile(idBook)
    }
}