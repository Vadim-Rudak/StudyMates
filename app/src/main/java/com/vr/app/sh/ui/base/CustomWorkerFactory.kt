package com.vr.app.sh.ui.base

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.vr.app.sh.domain.UseCase.ConnectToWebSocket
import com.vr.app.sh.worker.ChatWorker

class CustomWorkerFactory(
    private val connectToWebSocket: ConnectToWebSocket
):WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when(workerClassName){
            ChatWorker::class.java.name -> ChatWorker(appContext,workerParameters,connectToWebSocket)
            else -> null
        }
    }
}