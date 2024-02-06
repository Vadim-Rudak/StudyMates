package com.vr.app.sh.ui.messages.chat.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vr.app.sh.domain.UseCase.GetMessagesInChat
import com.vr.app.sh.domain.UseCase.SendMessage
import com.vr.app.sh.domain.model.messages.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ChatWithUserViewModel(
    context: Context,
    private val sendMessage: SendMessage,
    private val getMessagesInChat: GetMessagesInChat
):ViewModel() {

    var job: Job? = null

    fun sendMessage(nameChat:String, idUser:Int, idUserToSend:Int, text:String){
        val message = Message(idUserToSend,1,text)
        job = CoroutineScope(Dispatchers.IO).launch {
            sendMessage.execute(nameChat,idUser,message)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}