package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.data.model.Test
import com.vr.app.sh.domain.repository.TestsInternetRepo
import retrofit2.Response

class GetListTestsInternet(private val testsInternetRepo: TestsInternetRepo) {
    suspend fun execute(name_subject:String): Response<List<Test>> {
        return testsInternetRepo.getListTestsInSub(name_subject)
    }
}