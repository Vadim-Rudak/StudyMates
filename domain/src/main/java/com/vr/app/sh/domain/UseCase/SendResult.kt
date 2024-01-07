package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.ResultTest
import com.vr.app.sh.domain.model.response.SendResponse
import com.vr.app.sh.domain.repository.internet.ResultInternetRepo

class SendResult(private val resultInternetRepo: ResultInternetRepo) {
    suspend fun execute(result: ResultTest): SendResponse {
        return resultInternetRepo.sendResult(result)
    }
}