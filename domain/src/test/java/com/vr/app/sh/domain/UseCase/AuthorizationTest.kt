package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.AuthorizationEntity
import com.vr.app.sh.domain.repository.internet.DoorInSystemRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class AuthorizationTest {

    private val testDoorInSystemRepo = mock<DoorInSystemRepo>()

    @Test
    fun `send login and password`(){

        val testAuthorizationEntity = AuthorizationEntity(true)
        val login = "admin"
        val password = "admin"
        CoroutineScope(Dispatchers.IO).launch {
            Mockito.`when`(testDoorInSystemRepo.authorization(login, password)).thenReturn(testAuthorizationEntity)
            val actual = Authorization(testDoorInSystemRepo).execute(login, password)

            assertTrue(actual.status == true)
        }.cancel()
    }

    @Test
    fun `return null login and password`(){

        val testAuthorizationEntity = AuthorizationEntity(true)
        val login = " "
        val password = " "
        CoroutineScope(Dispatchers.IO).launch {
            Mockito.`when`(testDoorInSystemRepo.authorization(login, password)).thenReturn(testAuthorizationEntity)
            val actual = Authorization(testDoorInSystemRepo).execute(login, password)

            assertTrue(actual.status == false)
        }.cancel()
    }
}