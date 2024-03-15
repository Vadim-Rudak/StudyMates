package com.vr.app.sh.ui.messages.allUsers.viewModel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.domain.UseCase.GetChatIdByUser
import com.vr.app.sh.domain.UseCase.GetUsersAndSaveLocal
import com.vr.app.sh.domain.model.User
import com.vr.app.sh.ui.messages.allUsers.adapter.UsersViewAdapter
import com.vr.app.sh.ui.messages.chat.view.ChatWithUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllUsersViewModel(
    private val context: Context,
    private val getUsersAndSaveLocal: GetUsersAndSaveLocal,
    private val getChatIdByUser: GetChatIdByUser
): ViewModel() {

    val adapter = UsersViewAdapter(context)
    val openChat = MutableLiveData<Intent>()
    val listUsers = MutableLiveData<List<User>>()
    var job: Job? = null

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        job = CoroutineScope(Dispatchers.IO).launch {
            getUsersAndSaveLocal.execute().collectIndexed { _, value ->
                listUsers.postValue(value)
            }
        }
        adapter.setListener(object : UsersViewAdapter.Listener{
            override fun selectUser(user: User) {
                job = CoroutineScope(Dispatchers.IO).launch {
                    val intent = Intent(context, ChatWithUser::class.java).apply {
                        putExtra("chatId", getChatIdByUser.execute(user.id)?.chatId)
                        putExtra("userId",user.id)
                        putExtra("userName",user.name)
                        putExtra("lastName",user.lastName)
                    }
                    withContext(Dispatchers.Main) {
                        openChat.postValue(intent)
                    }
                }
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}