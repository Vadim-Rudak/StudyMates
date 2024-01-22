package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetListQuestions
import com.vr.app.sh.domain.UseCase.GetListTestsInClass
import com.vr.app.sh.domain.UseCase.SaveQuestionsInBD
import com.vr.app.sh.ui.other.InternetConnection
import com.vr.app.sh.ui.tests.viewmodel.TestsOneClassViewModel

class TestsOneClassViewModelFactory(
    val context: Context,
    private val getListTestsInClass: GetListTestsInClass,
    private val getListQuestions: GetListQuestions,
    private val saveQuestionsInBD: SaveQuestionsInBD
    ): ViewModelProvider.Factory {

    private var numClass:Int = 0

    fun setClass(num_class: Int){
        this.numClass = num_class
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TestsOneClassViewModel::class.java)) {
            TestsOneClassViewModel(
                resources = context.resources,
                getListTestsInClass = getListTestsInClass,
                getListQuestions = getListQuestions,
                saveQuestionsInBD = saveQuestionsInBD,
                numClass = numClass,
                internetConnect = InternetConnection.useInternet(context)
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}