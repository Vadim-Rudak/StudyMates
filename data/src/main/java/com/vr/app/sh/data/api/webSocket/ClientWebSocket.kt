package com.vr.app.sh.data.api.webSocket

import android.util.Log
import com.vr.app.sh.data.api.webSocket.InfoConnect.webSocketUri
import tech.gusavila92.websocketclient.WebSocketClient

class ClientWebSocket: WebSocketClient(webSocketUri) {

    override fun onOpen() {
        Log.i("WebSocket", "Session is starting")
    }

    override fun onTextReceived(message: String?) {
        Log.i("WebSocket", "Message received")
        Log.d("FFF","message=> $message")
    }
    override fun onBinaryReceived(data: ByteArray?) {}
    override fun onPingReceived(data: ByteArray?) {}
    override fun onPongReceived(data: ByteArray?) {}
    override fun onException(e: Exception?) {
        Log.i("WebSocketError", e?.message.toString())
    }
    override fun onCloseReceived() {
        Log.i("WebSocket", "Closed ")
        println("onCloseReceived")
    }
}