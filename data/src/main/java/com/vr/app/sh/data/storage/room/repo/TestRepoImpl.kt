package com.vr.app.sh.data.storage.room.repo

import com.vr.app.sh.data.storage.model.TestEntity
import com.vr.app.sh.data.storage.room.dao.DAOTest
import com.vr.app.sh.domain.model.Test
import com.vr.app.sh.domain.repository.local.TestRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class TestRepoImpl(private val testDAO:DAOTest): TestRepo {
    override suspend fun insertTests(listTests: List<Test>) {
        testDAO.insertTests(listTests.map { TestEntity(
            it.id,
            it.subject,
            it.num_class,
            it.name_test,
            it.num_questions
        ) })
    }

    override fun getTestsInOneClass(numClass: Int): Flow<List<Test>> {
        return testDAO.getTestsInOneClass(numClass).map{
            it.map {testEntity->
                testEntity.toTest()
            }
        }
    }
}