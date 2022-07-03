package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.data.repository.BookRepoImpl
import com.vr.app.sh.data.repository.InternetRepoImpl
import com.vr.app.sh.domain.UseCase.GetBookFile
import com.vr.app.sh.domain.UseCase.GetListBookInClass
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.Registration
import com.vr.app.sh.ui.books.viewmodel.SubjectsViewModel
import com.vr.app.sh.ui.door.viewmodel.RegViewModel

class BooksViewModelFactory(private val internetRepoImpl: InternetRepoImpl,private val bookRepoImpl: BookRepoImpl, context: Context,private val num_class:Int): ViewModelProvider.Factory  {

    val getListBookInClass by lazy(LazyThreadSafetyMode.NONE) { GetListBookInClass(bookRepoImpl) }
    val getBookFile by lazy(LazyThreadSafetyMode.NONE) { GetBookFile(internetRepoImpl) }
    val internetConnection by lazy(LazyThreadSafetyMode.NONE) { InternetConnection(context) }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SubjectsViewModel::class.java)) {
            SubjectsViewModel(getBookFile,getListBookInClass,internetConnection,num_class) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}