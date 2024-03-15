package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.messages.Message
import com.vr.app.sh.domain.repository.local.chat.MessageRepo

class SaveMessage(private val messageRepo: MessageRepo) {
    suspend fun execute(message: Message){
        messageRepo.insertMessage(message)
    }
}