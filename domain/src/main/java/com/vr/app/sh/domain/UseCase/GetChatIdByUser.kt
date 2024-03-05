package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.local.chat.UserInChatRepo

class GetChatIdByUser(private val userInChatRepo: UserInChatRepo) {
    fun execute(userId:Int) = userInChatRepo.getChatIdByUser(userId)
}