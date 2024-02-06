package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetMessagesInChat
import com.vr.app.sh.domain.UseCase.SendMessage
import com.vr.app.sh.ui.messages.chat.viewModel.ChatWithUserViewModel

class ChatViewModelFactory(
    private val context: Context,
    private val sendMessage: SendMessage,
    private val getMessagesInChat: GetMessagesInChat
): ViewModelProvider.Factory  {

    private var idChat:Int = 0
    private var userId:Int = 0

    fun initChatId(chatId:Int,userId:Int){
        idChat = chatId
        this.userId = userId
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ChatWithUserViewModel::class.java)) {
            ChatWithUserViewModel(context,idChat,userId,sendMessage,getMessagesInChat) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}