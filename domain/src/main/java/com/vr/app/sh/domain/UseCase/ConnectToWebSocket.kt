package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.internet.WebSocketRepo

class ConnectToWebSocket(private val webSocketRepo: WebSocketRepo) {

    suspend fun execute(){
        webSocketRepo.connect()
    }

}