package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Test
import com.vr.app.sh.domain.model.response.ListResponse
import com.vr.app.sh.domain.repository.internet.TestsInternetRepo

class GetListTestsInternet(private val testsInternetRepo: TestsInternetRepo) {
    suspend fun execute(nameSubject:String): ListResponse<Test> {
        return testsInternetRepo.getListTestsInSub(nameSubject)
    }
}