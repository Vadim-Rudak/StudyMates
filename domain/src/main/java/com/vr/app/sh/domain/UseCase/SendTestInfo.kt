package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.response.SendResponse
import com.vr.app.sh.domain.repository.internet.TestsInternetRepo

class SendTestInfo(private val testsInternetRepo: TestsInternetRepo) {
    suspend fun execute(nameSubject: String, numClass: Int, nameTest: String, size: Int): SendResponse {
        return testsInternetRepo.sendTest(nameSubject,numClass, nameTest, size)
    }
}