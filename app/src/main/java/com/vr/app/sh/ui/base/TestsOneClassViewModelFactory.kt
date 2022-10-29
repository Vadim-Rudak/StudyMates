package com.vr.app.sh.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetListQuestions
import com.vr.app.sh.domain.UseCase.GetListTestsInClass
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveQuestionsInBD
import com.vr.app.sh.ui.tests.viewmodel.TestsOneClassViewModel

class TestsOneClassViewModelFactory(
    val getListTestsInClass: GetListTestsInClass,
    val getListQuestions: GetListQuestions,
    val saveQuestionsInBD: SaveQuestionsInBD,
    val internetConnection: InternetConnection,
    ): ViewModelProvider.Factory {

    var num_class:Int = 0

    fun setClass(num_class: Int){
        this.num_class = num_class
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TestsOneClassViewModel::class.java)) {
            TestsOneClassViewModel(getListTestsInClass,getListQuestions,saveQuestionsInBD,internetConnection,num_class) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}