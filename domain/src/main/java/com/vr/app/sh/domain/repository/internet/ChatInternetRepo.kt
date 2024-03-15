package com.vr.app.sh.domain.repository.internet

import com.vr.app.sh.domain.model.messages.Message
import com.vr.app.sh.domain.model.response.InfoChat

interface ChatInternetRepo {
    suspend fun sendMessage(nameChat:String, idUserCreate:Int, message: Message):Message
    suspend fun getChatsInfo(userId:Int,idLastMessage:Int):InfoChat
}