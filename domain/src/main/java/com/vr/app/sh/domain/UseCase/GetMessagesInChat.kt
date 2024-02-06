package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.messages.Message
import com.vr.app.sh.domain.repository.local.chat.MessageRepo
import kotlinx.coroutines.flow.Flow

class GetMessagesInChat(private val messageRepo: MessageRepo) {

    fun execute(numDay:Int): Flow<List<Message>> {
        return messageRepo.getMessagesInChat(numDay)
    }

}