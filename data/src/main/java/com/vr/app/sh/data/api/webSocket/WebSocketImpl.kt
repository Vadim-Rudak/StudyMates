package com.vr.app.sh.data.api.webSocket

import com.vr.app.sh.domain.repository.internet.WebSocketRepo

class WebSocketImpl:WebSocketRepo {
    private val webSocketClient: ClientWebSocket = ClientWebSocket()

    override suspend fun connect() {
        webSocketClient.connect()
    }
}