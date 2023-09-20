package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.Registration
import com.vr.app.sh.domain.UseCase.SaveUser
import com.vr.app.sh.ui.door.viewmodel.RegViewModel

class RegViewModelFactory(
    val context: Context,
    val saveUser: SaveUser,
    val registration: Registration,
    val internetConnection: InternetConnection
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RegViewModel::class.java)) {
            RegViewModel(context.resources,saveUser,registration,internetConnection) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}