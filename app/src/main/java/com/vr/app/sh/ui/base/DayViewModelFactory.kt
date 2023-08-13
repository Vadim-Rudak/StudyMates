package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetLessonsInDay
import com.vr.app.sh.domain.UseCase.GetListQuestions
import com.vr.app.sh.domain.UseCase.GetListTestsInClass
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveLessonInBD
import com.vr.app.sh.domain.UseCase.SaveQuestionsInBD
import com.vr.app.sh.ui.tests.viewmodel.TestsOneClassViewModel
import com.vr.app.sh.ui.timeTable.viewModel.DayViewModel

class DayViewModelFactory(
    private val getLessonsInDay: GetLessonsInDay
): ViewModelProvider.Factory {

    var numDay:Int = 0

    fun setDay(numDay: Int){
        this.numDay = numDay
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DayViewModel::class.java)) {
            DayViewModel(getLessonsInDay,numDay) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}