package com.vr.app.sh.ui.timeTable.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.data.model.Lesson
import com.vr.app.sh.domain.UseCase.GetLessonsInDay
import com.vr.app.sh.domain.UseCase.SaveLessonInBD
import com.vr.app.sh.ui.timeTable.adapter.LessonItemDecoration
import com.vr.app.sh.ui.timeTable.adapter.LessonsAdapter

class DayViewModel(getLessonsInDay: GetLessonsInDay,numDay:Int): ViewModel() {

    val listLessons: LiveData<List<Lesson>> = getLessonsInDay.execute(numDay)
    val adapter = LessonsAdapter()


}