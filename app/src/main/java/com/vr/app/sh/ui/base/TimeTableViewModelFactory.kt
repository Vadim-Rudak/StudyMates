package com.vr.app.sh.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.SaveLessonInBD
import com.vr.app.sh.ui.timeTable.viewModel.TimeTableViewModel

class TimeTableViewModelFactory(
    private val saveLessonInBD: SaveLessonInBD
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TimeTableViewModel::class.java)) {
            TimeTableViewModel(saveLessonInBD) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}