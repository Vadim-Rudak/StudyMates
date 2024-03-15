package com.vr.app.sh.data.api

import android.content.Context
import com.vr.app.sh.domain.model.messages.Message
import com.vr.app.sh.domain.model.response.InfoChat
import com.vr.app.sh.domain.repository.internet.ChatInternetRepo
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class ChatInternetRepoImpl(val context: Context, private val networkService: NetworkService): ChatInternetRepo {
    override suspend fun sendMessage(nameChat:String, idUserCreate:Int, message: Message):Message {
        return networkService.sendMessage(nameChat,idUserCreate,jsonMessage(message)).body()!!
    }

    override suspend fun getChatsInfo(userId: Int, idLastMessage: Int): InfoChat {
        return networkService.getInfoChat(userId, idLastMessage).body()!!
    }


    private fun jsonMessage(message: Message): RequestBody {
        val jsonObjectString = JSONObject().apply {
            put("ID", message.id)
            put("chatid", message.chatId)
            put("usertosendid", message.userToSendId)
            put("type", message.type)
            put("timesend", message.timeSend)
            put("res", message.res)
        }.toString()

        return jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
    }
}