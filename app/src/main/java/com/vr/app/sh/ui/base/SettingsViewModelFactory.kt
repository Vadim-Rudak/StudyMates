package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.ClearUser
import com.vr.app.sh.domain.UseCase.GetAllBookListInternet
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveBookListInBD
import com.vr.app.sh.ui.books.viewmodel.AddBookViewModel
import com.vr.app.sh.ui.menu.viewModel.SettingsViewModel

class SettingsViewModelFactory(
    val context: Context,
    val clearUser: ClearUser
): ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            SettingsViewModel(context,clearUser) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}