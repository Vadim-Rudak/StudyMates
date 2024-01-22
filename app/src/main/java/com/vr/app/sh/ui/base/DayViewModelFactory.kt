package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetLessonsInDay
import com.vr.app.sh.ui.other.InternetConnection
import com.vr.app.sh.ui.timeTable.viewModel.DayViewModel

class DayViewModelFactory(
    private val context: Context,
    private val getLessonsInDay: GetLessonsInDay
): ViewModelProvider.Factory {

    private var numDay:Int = 0

    fun setDay(numDay: Int){
        this.numDay = numDay
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DayViewModel::class.java)) {
            DayViewModel(
                getLessonsInDay,
                numDay,
                InternetConnection.useInternet(context)
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}