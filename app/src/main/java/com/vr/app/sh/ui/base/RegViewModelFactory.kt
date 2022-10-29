package com.vr.app.sh.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.Registration
import com.vr.app.sh.ui.door.viewmodel.RegViewModel

class RegViewModelFactory(
    val registration: Registration,
    val internetConnection: InternetConnection
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RegViewModel::class.java)) {
            RegViewModel(registration,internetConnection) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}