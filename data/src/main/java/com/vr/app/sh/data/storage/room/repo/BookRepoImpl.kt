package com.vr.app.sh.data.storage.room.repo

import com.vr.app.sh.data.storage.model.BookEntity
import com.vr.app.sh.data.storage.room.dao.DAOBook
import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.domain.repository.local.BookRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepoImpl(private var bookDAO: DAOBook) : BookRepo {
    override suspend fun insertBooks(listBooks: List<Book>) {
        bookDAO.insertBooks(listBooks.map { BookEntity(it.id,it.num_class,it.name,it.image) })
    }

    override fun getBookInOneClass(numClass: Int): Flow<List<Book>> {
        return bookDAO.getBookInOneClass(numClass).map{
            it.map {bookEntity->
                bookEntity.toBook()
            }
        }
    }
}