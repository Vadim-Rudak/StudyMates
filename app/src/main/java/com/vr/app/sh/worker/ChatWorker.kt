package com.vr.app.sh.worker

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.vr.app.sh.domain.UseCase.ConnectToWebSocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatWorker (
    val context: Context,
    params: WorkerParameters,
    private val connectToWebSocket:ConnectToWebSocket
) : Worker(context, params) {

    override fun doWork(): Result {
        try {
            Log.d("FFF","Start WorkManager")
            CoroutineScope(Dispatchers.IO).launch{
                connectToWebSocket.execute()
            }
        } catch (ex: Exception) {
            return Result.failure() //или Result.retry()
        }
        return Result.success()
    }
}