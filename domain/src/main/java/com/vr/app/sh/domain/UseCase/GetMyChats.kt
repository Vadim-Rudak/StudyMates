package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.messages.UserInChat
import com.vr.app.sh.domain.repository.local.chat.UserInChatRepo
import kotlinx.coroutines.flow.Flow

class GetMyChats(
    private val userInChatRepo: UserInChatRepo
) {

    fun execute(): Flow<List<UserInChat>>{
        return userInChatRepo.getChatsWithUsers()
    }
}