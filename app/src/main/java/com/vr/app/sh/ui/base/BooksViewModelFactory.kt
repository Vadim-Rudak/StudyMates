package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetBookFile
import com.vr.app.sh.domain.UseCase.GetListBookInClass
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.ui.books.viewmodel.SubjectsViewModel

class BooksViewModelFactory(
    val context:Context,
    val getListBookInClass: GetListBookInClass,
    val getBookFile: GetBookFile,
    val internetConnection: InternetConnection
    ): ViewModelProvider.Factory  {

    var num_class: Int = 0

    fun addNumClass(num_class:Int){
        this.num_class = num_class
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SubjectsViewModel::class.java)) {
            SubjectsViewModel(context,getBookFile,getListBookInClass,internetConnection,num_class) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}