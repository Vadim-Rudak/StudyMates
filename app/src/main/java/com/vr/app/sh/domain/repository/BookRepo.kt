package com.vr.app.sh.domain.repository

import com.vr.app.sh.domain.model.Book

interface BookRepo {
    fun getBookInOneClass(num_class:Int): ArrayList<Book>
    fun saveBooksInDB(listBook: List<Book>)
}