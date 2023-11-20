package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.DownloadUserPhoto
import com.vr.app.sh.domain.UseCase.GetAllBookListInternet
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveBookListInBD
import com.vr.app.sh.ui.menu.viewModel.MenuViewModel

class MenuViewModelFactory(
    val context: Context,
    val getListBookInternet: GetAllBookListInternet,
    val saveListBookInBD: SaveBookListInBD,
    val internetConnection: InternetConnection,
    val downloadUserPhoto: DownloadUserPhoto
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            MenuViewModel(context,getListBookInternet,saveListBookInBD,internetConnection,downloadUserPhoto) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}