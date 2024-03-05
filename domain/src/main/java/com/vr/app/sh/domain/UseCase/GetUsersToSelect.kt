package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.local.chat.FavoriteRepo

class GetUsersToSelect(private val favoriteRepo: FavoriteRepo) {

    fun execute() = favoriteRepo.getToSelect()

}