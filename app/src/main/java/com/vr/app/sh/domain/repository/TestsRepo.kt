package com.vr.app.sh.domain.repository

import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.domain.model.Test

interface TestsRepo {
    fun getTestsInOneClass(num_class:Int): ArrayList<Test>
    fun saveTestsInDB(listTests: List<Test>)
}