package com.vr.app.sh.domain.UseCase

import androidx.lifecycle.LiveData
import com.vr.app.sh.data.model.Test
import com.vr.app.sh.data.repository.DAOTest

class GetListTestsInClass(private val testDAO: DAOTest) {
    fun execute(num_class:Int):LiveData<List<Test>>{
        return testDAO.getTestsInOneClass(num_class)
    }
}