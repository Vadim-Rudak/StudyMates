package com.vr.app.sh.domain.repository.local.chat

import com.vr.app.sh.domain.model.messages.UserInChat
import kotlinx.coroutines.flow.Flow

interface UserInChatRepo {

    suspend fun insertUserInChat(userInChat: UserInChat)
    suspend fun insertUsersInChat(listUserInChat: List<UserInChat>)

    fun getMyChats(): Flow<List<UserInChat>>

    fun getChatsWithUsers(): Flow<List<UserInChat>>

}