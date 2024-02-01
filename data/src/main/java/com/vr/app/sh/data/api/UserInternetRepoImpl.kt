package com.vr.app.sh.data.api

import android.content.Context
import com.vr.app.sh.domain.model.User
import com.vr.app.sh.domain.model.response.ListResponse
import com.vr.app.sh.domain.repository.internet.UserInternetRepo

class UserInternetRepoImpl(val context: Context, private val networkService: NetworkService): UserInternetRepo {
    override suspend fun getAllUsers(): ListResponse<User> {
        val listUsers = networkService.getAllUsers()
        return if (listUsers.isSuccessful){
            ListResponse(true,listUsers.body())
        }else{
            ListResponse()
        }
    }
}