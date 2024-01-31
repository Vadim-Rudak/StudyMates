package com.vr.app.sh.domain.repository.local.chat

import com.vr.app.sh.domain.model.messages.Chat

interface ChatRepo {

    suspend fun insertChat(chat:Chat)
    suspend fun insertChats(listChats:List<Chat>)

}