package com.vr.app.sh.ui.messages.allChats.viewModel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.domain.UseCase.GetChatIdByUser
import com.vr.app.sh.domain.UseCase.GetFavoriteUsers
import com.vr.app.sh.domain.model.User
import com.vr.app.sh.ui.messages.allChats.adapter.SelectedUsersViewAdapter
import com.vr.app.sh.ui.messages.allChats.view.AddFavoriteUsers
import com.vr.app.sh.ui.messages.chat.view.ChatWithUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllChatsViewModel(
    val context: Context,
    private val getFavoriteUsers: GetFavoriteUsers,
    private val getChatIdByUser: GetChatIdByUser
): ViewModel() {

    var job: Job? = null
    val adapterSelectedUsers = SelectedUsersViewAdapter(context)
    val openNewActivity = MutableLiveData<Intent>()


    init {
        getSelectedUsers()
    }

    private fun getSelectedUsers(){
        job = CoroutineScope(Dispatchers.Main).launch {
            getFavoriteUsers.execute().collectIndexed { _, value ->
                adapterSelectedUsers.setUsers(value)
            }
        }
        adapterSelectedUsers.setListener(object:SelectedUsersViewAdapter.Listener{
            override fun click(user: User) {
                job = CoroutineScope(Dispatchers.IO).launch {
                    val intent = Intent(context, ChatWithUser::class.java).apply {
                        putExtra("chatId", getChatIdByUser.execute(user.id)?.chatId)
                        putExtra("userId",user.id)
                        putExtra("userName",user.name)
                        putExtra("lastName",user.lastName)
                    }
                    withContext(Dispatchers.Main) {
                        openNewActivity.postValue(intent)
                    }
                }
            }

            override fun selectUser() {
                Intent(context, AddFavoriteUsers::class.java).also {
                    openNewActivity.postValue(it)
                }
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}