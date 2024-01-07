package com.vr.app.sh.domain.repository.internet

import com.vr.app.sh.domain.model.response.DownloadFile
import kotlinx.coroutines.flow.Flow

interface PhotoInternetRepo {
    suspend fun downloadPhoto(userId: Int,pathToSave:String): Flow<DownloadFile>
    suspend fun verificationUser(userId: Int)
}