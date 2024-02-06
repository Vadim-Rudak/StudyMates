package com.vr.app.sh.data.api.webSocket

import com.vr.app.sh.domain.repository.internet.WebSocketRepo

class WebSocketImpl(private val webSocketClient: ClientWebSocket):WebSocketRepo {

    override suspend fun connect() {
        webSocketClient.connect()
    }
}