package com.vr.app.sh.ui.messages.allChats.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.domain.UseCase.GetMyChats
import com.vr.app.sh.domain.model.messages.UserInChat
import com.vr.app.sh.ui.messages.allChats.adapter.ChatsViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

class MyChatsViewModel(
    val context: Context,
    private val getMyChats: GetMyChats
) :ViewModel(){

    val adapter = ChatsViewAdapter(context)
    val listChatWithUser = MutableLiveData<List<UserInChat>>()
    var job: Job? = null


    init {
        getMyChats()
    }

    private fun getMyChats(){
        job = CoroutineScope(Dispatchers.IO).launch {
            getMyChats.execute().collectIndexed { _, value ->
                listChatWithUser.postValue(value)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}