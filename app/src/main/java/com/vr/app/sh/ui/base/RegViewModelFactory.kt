package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.data.repository.InternetRepoImpl
import com.vr.app.sh.domain.UseCase.Authorization
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.Registration
import com.vr.app.sh.ui.door.view.Reg
import com.vr.app.sh.ui.door.viewmodel.RegViewModel

class RegViewModelFactory(private val internetRepoImpl: InternetRepoImpl, context: Context): ViewModelProvider.Factory {

    val registration by lazy(LazyThreadSafetyMode.NONE) { Registration(internetRepoImpl) }
    val internetConnection by lazy(LazyThreadSafetyMode.NONE) { InternetConnection(context) }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RegViewModel::class.java)) {
            RegViewModel(registration,internetConnection) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}