package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.User
import com.vr.app.sh.domain.repository.internet.UserInternetRepo
import com.vr.app.sh.domain.repository.local.user.UserRepo
import kotlinx.coroutines.flow.Flow


class GetUsersAndSaveLocal(
    private val userRepo: UserRepo,
    private val userInternetRepo: UserInternetRepo
) {

    suspend fun execute(): Flow<List<User>>{
        val listResponse = userInternetRepo.getAllUsers()
        if (listResponse.success){
            listResponse.list?.let {
                userRepo.insertUsers(it)
            }
        }
        return userRepo.getAllUsers()
    }

}