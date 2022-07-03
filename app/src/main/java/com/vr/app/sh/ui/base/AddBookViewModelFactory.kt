package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.data.repository.BookRepoImpl
import com.vr.app.sh.data.repository.InternetRepoImpl
import com.vr.app.sh.data.repository.UserRepoImpl
import com.vr.app.sh.domain.UseCase.GetAllBookListInternet
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveBookListInBD
import com.vr.app.sh.ui.books.viewmodel.AddBookViewModel
import com.vr.app.sh.ui.door.viewmodel.AuthViewModel

class AddBookViewModelFactory(private val bookRepoImpl: BookRepoImpl, private val internetRepoImpl: InternetRepoImpl, context: Context): ViewModelProvider.Factory {

    val saveBookListInBD by lazy(LazyThreadSafetyMode.NONE) { SaveBookListInBD(bookRepoImpl) }
    val getListAllBook by lazy(LazyThreadSafetyMode.NONE) { GetAllBookListInternet(internetRepoImpl) }
    val internetConnection by lazy(LazyThreadSafetyMode.NONE) { InternetConnection(context) }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AddBookViewModel::class.java)) {
            AddBookViewModel(getListAllBook,saveBookListInBD,internetConnection) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}