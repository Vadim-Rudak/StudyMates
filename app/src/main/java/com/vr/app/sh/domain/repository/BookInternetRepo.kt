package com.vr.app.sh.domain.repository

import com.vr.app.sh.data.model.Book
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

interface BookInternetRepo {
    suspend fun getAllBookList(): Response<List<Book>>
    fun getBookFile(id_book:Int): Call<ResponseBody>
}