package com.vr.app.sh.data.api.webSocket

import android.content.Context
import android.util.Log
import com.vr.app.sh.data.api.NetworkService
import com.vr.app.sh.data.api.webSocket.InfoConnect.webSocketUri
import com.vr.app.sh.data.storage.model.chat.MessageEntity
import com.vr.app.sh.data.storage.model.chat.UsersInChatEntity
import com.vr.app.sh.data.storage.model.users.UserEntity
import com.vr.app.sh.data.storage.room.dao.DAOMessage
import com.vr.app.sh.data.storage.room.dao.DAOUsersInChat
import com.vr.app.sh.data.storage.room.dao.user.DAOUser
import com.vr.app.sh.domain.model.response.InfoChat
import kotlinx.coroutines.*
import tech.gusavila92.websocketclient.WebSocketClient

class ClientWebSocket(
    private val context:Context,
    private val networkService: NetworkService,
    private val daoUsersInChat: DAOUsersInChat,
    private val daoUser: DAOUser,
    private val daoMessage: DAOMessage
): WebSocketClient(webSocketUri) {

    override fun onOpen() {
        Log.i("WebSocket", "Session is starting")
    }

    override fun onTextReceived(message: String?) {
        Log.i("WebSocket", "Message received")
        Log.d("FFF","message=> $message")

        when(message){
            "UpdateInfoChat"->{
                updateInfoChat()
            }
            else->{

            }
        }
    }

    private fun updateInfoChat(){
        val sharedPrefs = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)

        CoroutineScope(Dispatchers.IO).launch {
            val response = networkService.getInfoChat(sharedPrefs.getInt("id",0),0)
            if (response.isSuccessful){
                val infoChat: InfoChat = response.body()!!
                val listChats = infoChat.usersInChatList

                listChats?.forEach {
                    Log.d("FFF4",it.toString())
                }

                infoChat.messagesList?.forEach {
                    Log.d("FFF4",it.toString())
                }


                if (listChats!=null){
                    //see users in local bd and download from server
                    val listIds:ArrayList<Int> = arrayListOf()
                    listChats.forEach {
                        if (daoUser.findUser(it.userId!!)==null){
                            listIds.add(it.userId!!)
                        }
                    }

                    listIds.forEach {
                        Log.d("FFF","Up1000000042 $it")
                    }
                    if (listIds.size!=0){
                        val resp = networkService.getSelectedUsers(listIds)
                        Log.d("FFF","Up100000005 ${resp.body()!!.size}")
                        if (resp.isSuccessful){
                            daoUser.insertUsers(resp.body()!!.map {
                                UserEntity(
                                id = it.id,
                                name = it.name,
                                lastName = it.lastName,
                                gender = it.gender,
                                dateBirthday = it.dateBirthday,
                                cityLive = it.cityLive
                            )})
                        }
                    }

                    daoUsersInChat.insertUsersInChat(listChats.map {
                        UsersInChatEntity(
                            id = it.id,
                            chatId = it.chatId,
                            userId = it.userId,
                            group = it.group
                        )
                    })

                }
                if (infoChat.messagesList!=null){
                    daoMessage.insertMessages(infoChat.messagesList!!.map {
                        MessageEntity(
                            id = it.id,
                            chatId = it.chatId,
                            userToSendId = it.userToSendId,
                            type = it.type,
                            timeSend = it.timeSend,
                            res = it.res
                        )
                    })
                }
            }
        }
    }
    override fun onBinaryReceived(data: ByteArray?) {}
    override fun onPingReceived(data: ByteArray?) {}
    override fun onPongReceived(data: ByteArray?) {}
    override fun onException(e: Exception?) {
        Log.i("WebSocketError", e?.message.toString())
    }
    override fun onCloseReceived() {
        Log.i("WebSocket", "Closed ")
        println("onCloseReceived")
    }
}