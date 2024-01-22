package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.CleanCookie
import com.vr.app.sh.domain.UseCase.CleanUser
import com.vr.app.sh.domain.UseCase.DownloadUserPhoto
import com.vr.app.sh.domain.UseCase.GetAllBookListInternet
import com.vr.app.sh.domain.UseCase.SaveBookListInBD
import com.vr.app.sh.ui.menu.viewModel.MenuViewModel
import com.vr.app.sh.ui.other.InternetConnection

class MenuViewModelFactory(
    val context: Context,
    private val getListBookInternet: GetAllBookListInternet,
    private val saveListBookInBD: SaveBookListInBD,
    private val downloadUserPhoto: DownloadUserPhoto,
    private val cleanUser: CleanUser,
    private val cleanCookie: CleanCookie
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            MenuViewModel(
                context = context,
                getAllBookListInternet = getListBookInternet,
                saveBookListInBD = saveListBookInBD,
                downloadUserPhoto = downloadUserPhoto,
                cleanUser = cleanUser,
                cleanCookie = cleanCookie,
                internetConnect = InternetConnection.useInternet(context)
                ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}