package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.model.Test
import com.vr.app.sh.domain.repository.local.TestRepo
import kotlinx.coroutines.flow.Flow

class GetListTestsInClass(private val testRepo: TestRepo) {
    fun execute(numClass:Int): Flow<List<Test>> {
        return testRepo.getTestsInOneClass(numClass)
    }
}