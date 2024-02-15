package com.vr.app.sh.ui.messages.allChats.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vr.app.sh.domain.UseCase.GetMyChats
import com.vr.app.sh.ui.messages.allChats.adapter.SelectedUsersViewAdapter

class AllChatsViewModel(context: Context): ViewModel() {

    val adapterSelectedUsers = SelectedUsersViewAdapter(context)



}