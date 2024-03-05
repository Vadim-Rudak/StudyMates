package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetChatIdByUser
import com.vr.app.sh.domain.UseCase.GetFavoriteUsers
import com.vr.app.sh.domain.UseCase.GetUsersAndSaveLocal
import com.vr.app.sh.ui.messages.allChats.viewModel.AllChatsViewModel
import com.vr.app.sh.ui.messages.allUsers.viewModel.AllUsersViewModel

class SelectChatsViewModelFactory(
    private val context: Context,
    private val getFavoriteUsers: GetFavoriteUsers,
    private val getChatIdByUser: GetChatIdByUser
): ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AllChatsViewModel::class.java)) {
            AllChatsViewModel(context,getFavoriteUsers,getChatIdByUser) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}