package com.vr.app.sh.data.storage.room.repo

import com.vr.app.sh.data.storage.model.chat.MessageEntity
import com.vr.app.sh.data.storage.room.dao.DAOMessage
import com.vr.app.sh.domain.model.messages.Message
import com.vr.app.sh.domain.repository.local.chat.MessageRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MessagesRepoImpl(private val daoMessage: DAOMessage):MessageRepo {
    override suspend fun insertMessage(message: Message) {
        daoMessage.insertMessage(MessageEntity(
            id = message.id,
            chatId = message.chatId,
            userToSendId = message.userToSendId,
            type = message.type,
            timeSend = message.timeSend,
            res = message.res
        ))
    }

    override suspend fun insertMessages(listMessages: List<Message>) {
        daoMessage.insertMessages(listMessages.map { MessageEntity(
            id = it.id,
            chatId = it.chatId,
            userToSendId = it.userToSendId,
            type = it.type,
            timeSend = it.timeSend,
            res = it.res
        ) })
    }

    override fun getMessagesInChat(idChat: Int): Flow<List<Message>> {
        return daoMessage.getMessagesInChat(idChat).map { it.map {messageEntity ->
            messageEntity.toMessage()
        }}
    }
}