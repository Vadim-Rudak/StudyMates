package com.vr.app.sh.worker

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class ChatWorker (
    val context: Context,
    params: WorkerParameters,
) : Worker(context, params) {

    override fun doWork(): Result {
        val workManagerIntent = Intent(applicationContext, WebSocketForegroundService::class.java)
        ContextCompat.startForegroundService(applicationContext, workManagerIntent)
        return Result.success()
    }
}