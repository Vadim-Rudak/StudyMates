package com.vr.app.sh.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetAllBookListInternet
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveBookListInBD
import com.vr.app.sh.ui.menu.viewModel.MenuViewModel

class MenuViewModelFactory(
    val getListBookInternet: GetAllBookListInternet,
    val saveListBookInBD: SaveBookListInBD,
    val internetConnection: InternetConnection
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            MenuViewModel(getListBookInternet,saveListBookInBD,internetConnection) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}