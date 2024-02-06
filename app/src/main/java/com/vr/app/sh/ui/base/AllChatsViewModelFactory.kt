package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetUsersAndSaveLocal
import com.vr.app.sh.ui.messages.allUsers.viewModel.AllUsersViewModel

class AllChatsViewModelFactory(
    private val context: Context,
    private val getUsersAndSaveLocal: GetUsersAndSaveLocal
): ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AllUsersViewModel::class.java)) {
            AllUsersViewModel(context,getUsersAndSaveLocal) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}