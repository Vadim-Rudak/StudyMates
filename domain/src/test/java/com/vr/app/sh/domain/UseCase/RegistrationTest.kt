package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Auth
import com.vr.app.sh.domain.model.Photo
import com.vr.app.sh.domain.model.Reg
import com.vr.app.sh.domain.model.School
import com.vr.app.sh.domain.model.User
import com.vr.app.sh.domain.repository.internet.DoorInSystemRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.io.File

class RegistrationTest {

    private val testDoorInSystemRepo = mock<DoorInSystemRepo>()

    @Test
    fun `return status reg true when send data`(){

        val testReg = Reg(true)
        val user = User().also {
            it.id = 0
            it.name = "admin"
            it.lastName = "admin"
            it.gender = "man"
            it.dateBirthday = "01.01.1900"
            it.cityLive = "Minsk"
            it.auth = Auth()
            it.school = School()
            it.photo = Photo()
        }
        val photo = File("")



        CoroutineScope(Dispatchers.IO).launch {
            Mockito.`when`(testDoorInSystemRepo.registration(user,photo)).thenReturn(testReg)
            val actual = Registration(testDoorInSystemRepo).execute(user,photo)

            assertTrue(actual.statusReg!!)
        }.cancel()
    }

}