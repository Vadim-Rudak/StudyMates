package com.vr.app.sh.domain.repository.local

import com.vr.app.sh.domain.model.Test
import kotlinx.coroutines.flow.Flow

interface TestRepo {
    suspend fun insertTests(listTests:List<Test>)
    fun getTestsInOneClass(numClass:Int):Flow<List<Test>>
}