package com.vr.app.sh.domain.repository.internet

interface WebSocketRepo {
    suspend fun connect()
}