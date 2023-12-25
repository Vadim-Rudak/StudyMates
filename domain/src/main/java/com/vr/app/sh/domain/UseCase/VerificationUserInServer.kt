package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.internet.PhotoInternetRepo

class VerificationUserInServer(private val photoInternetRepo: PhotoInternetRepo) {
    suspend fun execute(idUser:Int){
        photoInternetRepo.verificationUser(idUser)
    }
}