package com.vr.app.sh.worker

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.vr.app.sh.R
import com.vr.app.sh.data.api.NetworkService
import com.vr.app.sh.data.api.webSocket.ClientWebSocket
import com.vr.app.sh.data.storage.room.RoomDB

class WebSocketForegroundService : Service() {

    private lateinit var clientWebSocket: ClientWebSocket
    private val NOTIFICATION_ID = 123 // Unique notification ID

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Start the service in the foreground
        startForeground(NOTIFICATION_ID, createNotification())

        // Start the WebSocket connection
        startWebSocket()

        return START_STICKY
    }

    private fun createNotification(): Notification {
        // Create a notification channel if required (for Android Oreo and above)
        createNotificationChannel()

        // Create a notification builder
        val notificationBuilder = NotificationCompat.Builder(this, "123")
            .setSmallIcon(R.drawable.book_image)
            .setContentTitle("StudyMates")
            .setContentText("Server connection is active")

        // Build the notification
        return notificationBuilder.build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "123",
                "WebSocketForegroundService Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun startWebSocket() {
        // Initialize and start the WebSocket connection
        clientWebSocket = ClientWebSocket(
            context = applicationContext,
            networkService = NetworkService.getInstance(applicationContext),
            daoUsersInChat = RoomDB.getDatabase(applicationContext).usersInChatDAO(),
            daoUser = RoomDB.getDatabase(applicationContext).userDAO(),
            daoMessage = RoomDB.getDatabase(applicationContext).messagesDAO()
        )
        clientWebSocket.connect()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Disconnect WebSocket when the service is destroyed
        clientWebSocket.close()
    }
}
