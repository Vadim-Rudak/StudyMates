package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.VerificationUserInServer
import com.vr.app.sh.ui.door.viewmodel.VerificationViewModel

class VerificationViewModelFactory(
    val context: Context,
    val verificationUserInServer: VerificationUserInServer
): ViewModelProvider.Factory  {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(VerificationViewModel::class.java)) {
            VerificationViewModel(context,verificationUserInServer) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}