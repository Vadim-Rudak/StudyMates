package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.Authorization
import com.vr.app.sh.domain.UseCase.ClearUser
import com.vr.app.sh.domain.UseCase.DownloadUserPhoto
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveUser
import com.vr.app.sh.ui.door.viewmodel.AuthViewModel

class AuthorizationViewModelFactory(
    val context: Context,
    val clearUser: ClearUser,
    val downloadUserPhoto: DownloadUserPhoto,
    val authorization: Authorization,
    val saveUser: SaveUser,
    val internetConnection: InternetConnection
): ViewModelProvider.Factory  {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            AuthViewModel(context.resources,clearUser,downloadUserPhoto,authorization,saveUser,internetConnection) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}