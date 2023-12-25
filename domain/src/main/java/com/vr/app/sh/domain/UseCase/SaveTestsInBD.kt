package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Test
import com.vr.app.sh.domain.repository.local.TestRepo

class SaveTestsInBD(private val testRepo: TestRepo) {
    suspend fun execute(listTests:List<Test>){
        testRepo.insertTests(listTests)
    }
}