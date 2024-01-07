package com.vr.app.sh.domain.repository.internet

import com.vr.app.sh.domain.model.Test
import com.vr.app.sh.domain.model.response.ListResponse
import com.vr.app.sh.domain.model.response.SendResponse

interface TestsInternetRepo {
    suspend fun getListTestsInSub(nameSubject:String): ListResponse<Test>
    suspend fun sendTest(nameSubject: String, numClass: Int, nameTest: String, size: Int): SendResponse
}