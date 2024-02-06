package com.vr.app.sh.domain.repository.local.chat

import com.vr.app.sh.domain.model.messages.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepo {

    suspend fun insertMessage(message: Message)
    suspend fun insertMessages(listMessages:List<Message>)

    fun getMessagesInChat(idChat:Int): Flow<List<Message>>

}