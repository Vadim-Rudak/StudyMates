package com.vr.app.sh.ui.messages.allChats.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vr.app.sh.domain.UseCase.AddFavoriteUser
import com.vr.app.sh.domain.UseCase.DeleteFavoriteUser
import com.vr.app.sh.domain.UseCase.GetUsersToSelect
import com.vr.app.sh.domain.model.messages.FavoriteUser
import com.vr.app.sh.ui.messages.allChats.adapter.AddFavoriteUsersViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

class FavoriteUsersViewModel(
    context:Context,
    private val getUsersToSelect: GetUsersToSelect,
    private val addFavoriteUser: AddFavoriteUser,
    private val deleteFavoriteUser: DeleteFavoriteUser,
): ViewModel() {

    var job: Job? = null
    val adapter = AddFavoriteUsersViewAdapter(context)

    init {
        getAllUsers()
    }

    private fun getAllUsers(){
        job = CoroutineScope(Dispatchers.Main).launch {
            getUsersToSelect.execute().collectIndexed { _, value ->
                adapter.setUsers(value)
            }
        }
        adapter.setListener(object:AddFavoriteUsersViewAdapter.Listener{
            override fun selectUser(favoriteUser: FavoriteUser) {
                job = CoroutineScope(Dispatchers.IO).launch {
                    if (favoriteUser.userId==null){
                        favoriteUser.userId = favoriteUser.user?.id
                        addFavoriteUser.execute(favoriteUser)
                    }else{
                        deleteFavoriteUser.execute(favoriteUser.userId!!)
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