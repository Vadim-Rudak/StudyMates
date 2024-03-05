package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.local.chat.FavoriteRepo

class DeleteFavoriteUser(private val favoriteRepo: FavoriteRepo) {
    fun execute(id:Int){
        favoriteRepo.deleteByUserId(id)
    }
}