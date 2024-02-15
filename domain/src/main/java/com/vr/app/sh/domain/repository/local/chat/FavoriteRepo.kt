package com.vr.app.sh.domain.repository.local.chat

import com.vr.app.sh.domain.model.messages.FavoriteUser
import kotlinx.coroutines.flow.Flow

interface FavoriteRepo {
    suspend fun insert(favoriteUser: FavoriteUser)
    fun getAll(): Flow<List<FavoriteUser>>
}