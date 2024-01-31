package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.Authorization
import com.vr.app.sh.domain.UseCase.CleanUser
import com.vr.app.sh.domain.UseCase.ConnectToWebSocket
import com.vr.app.sh.domain.UseCase.DownloadUserPhoto
import com.vr.app.sh.domain.UseCase.SaveUser
import com.vr.app.sh.ui.door.viewmodel.AuthViewModel
import com.vr.app.sh.ui.other.InternetConnection

class AuthorizationViewModelFactory(
    val context: Context,
    private val connectToWebSocket: ConnectToWebSocket,
    private val cleanUser: CleanUser,
    private val downloadUserPhoto: DownloadUserPhoto,
    private val authorization: Authorization,
    private val saveUser: SaveUser
): ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            AuthViewModel(
                resources = context.resources,
                connectToWebSocket = connectToWebSocket,
                cleanUser = cleanUser,
                downloadUserPhoto = downloadUserPhoto,
                authorization = authorization,
                saveUser = saveUser,
                internetConnect = InternetConnection.useInternet(context)
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}