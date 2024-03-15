package com.vr.app.sh.data.storage.room.repo

import android.util.Log
import com.vr.app.sh.data.storage.model.chat.UsersInChatEntity
import com.vr.app.sh.data.storage.room.dao.DAOUsersInChat
import com.vr.app.sh.domain.model.messages.UserInChat
import com.vr.app.sh.domain.repository.local.chat.UserInChatRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UsersInChatRepoImpl(private val daoUsersInChat: DAOUsersInChat):UserInChatRepo {
    override suspend fun insertUserInChat(userInChat: UserInChat) {
        daoUsersInChat.insertUserInChat(UsersInChatEntity(
            id = userInChat.id,
            chatId = userInChat.chatId,
            userId = userInChat.userId,
            group = userInChat.group
        ))
    }

    override suspend fun insertUsersInChat(listUserInChat: List<UserInChat>) {
        daoUsersInChat.insertUsersInChat(listUserInChat.map {
            UsersInChatEntity(
                id = it.id,
                chatId = it.chatId,
                userId = it.userId,
                group = it.group
            )
        })
    }

    override fun getMyChats(): Flow<List<UserInChat>> {
        return daoUsersInChat.getAllChats().map { it.map { usersInChatEntity->
            usersInChatEntity.toUserInChat()
        }}
    }

    override fun getChatsWithUsers(): Flow<List<UserInChat>> {
        return daoUsersInChat.getAllChatsWithUsers().map {
            it.map {userInChatEntity->
                userInChatEntity.toUserInChat()
            }
        }
    }

    override fun getChatIdByUser(userId: Int):UserInChat?{
        return daoUsersInChat.getChatId(userId)?.toUserInChat()
    }
}