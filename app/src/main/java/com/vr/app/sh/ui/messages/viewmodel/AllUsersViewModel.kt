package com.vr.app.sh.ui.messages.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.domain.UseCase.GetUsersAndSaveLocal
import com.vr.app.sh.domain.model.User
import com.vr.app.sh.ui.messages.adapter.UsersViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

class AllUsersViewModel(context: Context,private val getUsersAndSaveLocal: GetUsersAndSaveLocal): ViewModel() {

    val adapter = UsersViewAdapter(context)
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
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}