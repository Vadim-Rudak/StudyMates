package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Test
import com.vr.app.sh.domain.repository.TestsRepo

class GetListTestsInClass(private val testsRepo: TestsRepo) {
    fun execute(num_class:Int):ArrayList<Test>{
        return testsRepo.getTestsInOneClass(num_class)
    }
}