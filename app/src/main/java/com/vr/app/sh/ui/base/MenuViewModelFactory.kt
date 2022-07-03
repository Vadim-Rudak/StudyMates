package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.data.repository.BookRepoImpl
import com.vr.app.sh.data.repository.InternetRepoImpl
import com.vr.app.sh.data.repository.UserRepoImpl
import com.vr.app.sh.domain.UseCase.GetAllBookListInternet
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.Registration
import com.vr.app.sh.domain.UseCase.SaveBookListInBD
import com.vr.app.sh.ui.door.viewmodel.RegViewModel
import com.vr.app.sh.ui.menu.viewModel.MenuViewModel

class MenuViewModelFactory(private val internetRepoImpl: InternetRepoImpl, private val bookRepoImpl: BookRepoImpl, context: Context): ViewModelProvider.Factory {

    val getListBookInternet by lazy(LazyThreadSafetyMode.NONE) { GetAllBookListInternet(internetRepoImpl) }
    val saveListBookInBD by lazy(LazyThreadSafetyMode.NONE) { SaveBookListInBD(bookRepoImpl) }
    val internetConnection by lazy(LazyThreadSafetyMode.NONE) { InternetConnection(context) }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            MenuViewModel(getListBookInternet,saveListBookInBD,internetConnection) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}