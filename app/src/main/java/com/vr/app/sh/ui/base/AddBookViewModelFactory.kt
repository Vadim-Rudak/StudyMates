package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetAllBookListInternet
import com.vr.app.sh.domain.UseCase.SaveBookListInBD
import com.vr.app.sh.domain.UseCase.SendBook
import com.vr.app.sh.ui.books.viewmodel.AddBookViewModel
import com.vr.app.sh.ui.other.InternetConnection

class AddBookViewModelFactory(
    val context: Context,
    private val getAllBookListInternet:GetAllBookListInternet,
    private val saveBookListInBD:SaveBookListInBD,
    private val sendBook: SendBook
): ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AddBookViewModel::class.java)) {
            AddBookViewModel(
                resources = context.resources,
                getAllBookListInternet = getAllBookListInternet,
                saveBookListInBD = saveBookListInBD,
                internetConnection = InternetConnection.useInternet(context),
                sendBook = sendBook
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}