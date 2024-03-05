package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.messages.FavoriteUser
import com.vr.app.sh.domain.repository.local.chat.FavoriteRepo

class AddFavoriteUser(private val favoriteRepo: FavoriteRepo) {
    suspend fun execute(favoriteUser: FavoriteUser){
        favoriteRepo.insert(favoriteUser)
    }
}