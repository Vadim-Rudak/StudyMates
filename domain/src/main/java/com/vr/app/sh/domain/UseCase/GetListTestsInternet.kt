package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Test
import com.vr.app.sh.domain.repository.internet.TestsInternetRepo
import retrofit2.Response

class GetListTestsInternet(private val testsInternetRepo: TestsInternetRepo) {
    suspend fun execute(nameSubject:String): Response<List<Test>> {
        return testsInternetRepo.getListTestsInSub(nameSubject)
    }
}