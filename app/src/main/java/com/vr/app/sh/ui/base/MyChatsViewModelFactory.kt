package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetMyChats
import com.vr.app.sh.domain.UseCase.GetUsersAndSaveLocal
import com.vr.app.sh.ui.messages.allChats.viewModel.MyChatsViewModel
import com.vr.app.sh.ui.messages.allUsers.viewModel.AllUsersViewModel

class MyChatsViewModelFactory(
    private val context: Context,
    private val getMyChats: GetMyChats
): ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MyChatsViewModel::class.java)) {
            MyChatsViewModel(context,getMyChats) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}