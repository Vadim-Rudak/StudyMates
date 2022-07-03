package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetUserBD
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SendResult
import com.vr.app.sh.domain.repository.ResultInternetRepo
import com.vr.app.sh.domain.repository.UserRepo
import com.vr.app.sh.ui.tests.viewmodel.ResultViewModel
import com.vr.app.sh.ui.tests.viewmodel.TestsOneClassViewModel

class ResultViewModelFactory(private val resultInternetRepo: ResultInternetRepo,private val userRepo: UserRepo,context: Context): ViewModelProvider.Factory {

    val sendResult by lazy(LazyThreadSafetyMode.NONE) { SendResult(resultInternetRepo) }
    val getUser by lazy(LazyThreadSafetyMode.NONE) { GetUserBD(userRepo) }
    val internetConnection by lazy(LazyThreadSafetyMode.NONE) { InternetConnection(context) }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            ResultViewModel(internetConnection,getUser,sendResult) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}