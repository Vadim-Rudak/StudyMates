package com.vr.app.sh.domain.repository.local.chat

import com.vr.app.sh.domain.model.messages.UserInChat

interface UserInChatRepo {

    suspend fun insertUserInChat(userInChat: UserInChat)
    suspend fun insertUsersInChat(listUserInChat: List<UserInChat>)

}