package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetAllBookListInternet
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveBookListInBD
import com.vr.app.sh.ui.books.viewmodel.AddBookViewModel

class AddBookViewModelFactory(
    val context: Context,
    val getAllBookListInternet:GetAllBookListInternet,
    val saveBookListInBD:SaveBookListInBD,
    val internetConnection: InternetConnection
): ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AddBookViewModel::class.java)) {
            AddBookViewModel(context.resources,getAllBookListInternet,saveBookListInBD,internetConnection) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}