package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.messages.Message
import com.vr.app.sh.domain.repository.internet.ChatInternetRepo

class SendMessage(private val chatInternetRepo: ChatInternetRepo) {

    suspend fun execute(nameChat:String, idUserCreate:Int, message: Message):Message{
        return chatInternetRepo.sendMessage(nameChat, idUserCreate, message)
    }
}