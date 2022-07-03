package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.data.repository.InternetRepoImpl
import com.vr.app.sh.data.repository.QuestionsRepoImpl
import com.vr.app.sh.data.repository.TestsRepoImpl
import com.vr.app.sh.domain.UseCase.*
import com.vr.app.sh.ui.tests.viewmodel.TestsOneClassViewModel


class TestsOneClassViewModelFactory(private val internetRepoImpl: InternetRepoImpl,private val testsRepoImpl: TestsRepoImpl,private val questionsRepoImpl: QuestionsRepoImpl,context: Context,private val num_class:Int): ViewModelProvider.Factory {

    val getListTestsInClass by lazy(LazyThreadSafetyMode.NONE) { GetListTestsInClass(testsRepoImpl) }
    val getListQuestions by lazy(LazyThreadSafetyMode.NONE) { GetListQuestions(internetRepoImpl)}
    val saveQuestionsInBD by lazy(LazyThreadSafetyMode.NONE) { SaveQuestionsInBD(questionsRepoImpl)}
    val internetConnection by lazy(LazyThreadSafetyMode.NONE) { InternetConnection(context) }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TestsOneClassViewModel::class.java)) {
            TestsOneClassViewModel(getListTestsInClass,getListQuestions,saveQuestionsInBD,internetConnection,num_class) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}