package com.vr.app.sh.domain.repository.internet

import com.vr.app.sh.domain.model.ResultTest
import com.vr.app.sh.domain.model.response.SendResponse

interface ResultInternetRepo {
    suspend fun sendResult(result: ResultTest): SendResponse
}