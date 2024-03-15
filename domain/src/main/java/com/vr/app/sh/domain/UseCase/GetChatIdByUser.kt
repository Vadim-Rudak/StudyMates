package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.local.chat.UserInChatRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetChatIdByUser(private val userInChatRepo: UserInChatRepo) {
    suspend fun execute(userId:Int)= withContext(Dispatchers.IO) {userInChatRepo.getChatIdByUser(userId)}
}