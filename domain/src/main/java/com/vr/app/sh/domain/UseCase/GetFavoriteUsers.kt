package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.local.chat.FavoriteRepo

class GetFavoriteUsers(private val favoriteRepo: FavoriteRepo) {
    fun execute() = favoriteRepo.getAll()
}