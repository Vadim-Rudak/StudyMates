package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Test
import com.vr.app.sh.domain.repository.TestsRepo

class SaveTestsInBD(private val testsRepo: TestsRepo) {
    fun execute(ListTests:List<Test>){
        testsRepo.saveTestsInDB(ListTests)
    }
}