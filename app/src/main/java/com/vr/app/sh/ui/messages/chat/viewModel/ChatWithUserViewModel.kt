package com.vr.app.sh.ui.messages.chat.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.domain.UseCase.GetMessagesInChat
import com.vr.app.sh.domain.UseCase.SendMessage
import com.vr.app.sh.domain.model.messages.Message
import com.vr.app.sh.ui.messages.chat.adapter.ChatViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

class ChatWithUserViewModel(
    context: Context,
    private val chatId:Int,
    userId:Int,
    private val sendMessage: SendMessage,
    private val getMessagesInChat: GetMessagesInChat
):ViewModel() {

    val adapter = ChatViewAdapter(context,userId)
    val listMessages = MutableLiveData<List<Message>>()
    var job: Job? = null

    init {
        getMsgInChat()
    }

    fun sendMessage(nameChat:String, idUser:Int, idUserToSend:Int, text:String){
        val message = Message(chatId,idUserToSend,1,text)
        job = CoroutineScope(Dispatchers.IO).launch {
            sendMessage.execute(nameChat,idUser,message)
        }
    }

    private fun getMsgInChat(){
        job = CoroutineScope(Dispatchers.IO).launch {
            getMessagesInChat.execute(chatId).collectIndexed { index, value ->
                listMessages.postValue(value)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}