package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.data.model.Test
import com.vr.app.sh.data.repository.DAOTest

class SaveTestsInBD(private val testsDAO: DAOTest) {
    suspend fun execute(ListTests:List<Test>){
        testsDAO.insertTests(ListTests)
    }
}