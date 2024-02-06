package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.response.InfoChat
import com.vr.app.sh.domain.repository.internet.ChatInternetRepo
import com.vr.app.sh.domain.repository.local.chat.MessageRepo
import com.vr.app.sh.domain.repository.local.chat.UserInChatRepo

class UpdateInfoChat(
    private val userInChatRepo: UserInChatRepo,
    private val messageRepo: MessageRepo,
    private val chatInternetRepo: ChatInternetRepo
) {
    suspend fun execute(userId:Int, idLastMessage:Int){
        val infoChat:InfoChat = chatInternetRepo.getChatsInfo(userId, idLastMessage)
        if (infoChat.usersInChatList!=null){
            userInChatRepo.insertUsersInChat(infoChat.usersInChatList!!)
        }
        if (infoChat.messagesList!=null){
            messageRepo.insertMessages(infoChat.messagesList!!)
        }
    }
}