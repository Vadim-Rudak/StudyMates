package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.PhotoInternetRepo

class VerificationUserInServer(private val photoInternetRepo: PhotoInternetRepo) {
    suspend fun execute(user_id:Int){
        photoInternetRepo.verificationUser(user_id)
    }
}