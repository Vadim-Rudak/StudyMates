package com.vr.app.sh.domain.UseCase

import androidx.lifecycle.LiveData
import com.vr.app.sh.data.model.Book
import com.vr.app.sh.data.repository.DAOBook

class GetListBookInClass(private val bookRepo: DAOBook) {
    fun execute(num_class:Int): LiveData<List<Book>> {
        return bookRepo.getBookInOneClass(num_class)
    }
}