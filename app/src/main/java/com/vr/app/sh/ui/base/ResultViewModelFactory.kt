package com.vr.app.sh.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetUserBD
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SendResult
import com.vr.app.sh.ui.tests.viewmodel.ResultViewModel

class ResultViewModelFactory(
    val sendResult: SendResult,
    val getUser: GetUserBD,
    val internetConnection: InternetConnection
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            ResultViewModel(internetConnection,getUser,sendResult) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}