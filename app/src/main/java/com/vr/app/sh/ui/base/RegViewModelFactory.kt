package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.Registration
import com.vr.app.sh.domain.UseCase.SaveUser
import com.vr.app.sh.ui.door.viewmodel.RegViewModel
import com.vr.app.sh.ui.other.InternetConnection

class RegViewModelFactory(
    val context: Context,
    private val saveUser: SaveUser,
    private val registration: Registration
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RegViewModel::class.java)) {
            RegViewModel(
                resources = context.resources,
                saveUser = saveUser,
                registration = registration,
                internetConnect = InternetConnection.useInternet(context)
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}