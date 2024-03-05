package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.*
import com.vr.app.sh.domain.UseCase.*
import com.vr.app.sh.ui.messages.allChats.viewModel.FavoriteUsersViewModel

class FavoriteUsersViewModelFactory(
    private val context: Context,
    private val getUsersToSelect: GetUsersToSelect,
    private val addFavoriteUser: AddFavoriteUser,
    private val deleteFavoriteUser: DeleteFavoriteUser
): ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FavoriteUsersViewModel::class.java)) {
            FavoriteUsersViewModel(context,getUsersToSelect,addFavoriteUser,deleteFavoriteUser) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}