package com.vr.app.sh.ui.timeTable.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.domain.UseCase.GetLessonsInDay
import com.vr.app.sh.domain.model.Lesson
import com.vr.app.sh.ui.timeTable.adapter.LessonsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

class DayViewModel(private val getLessonsInDay: GetLessonsInDay,private val numDay:Int,private val internetConnect:Boolean): ViewModel() {

    val listLessons = MutableLiveData<List<Lesson>>()
    val adapter = LessonsAdapter()
    var job: Job? = null

    init {
        fetchLessons()
    }
    private fun fetchLessons () {
        job = CoroutineScope(Dispatchers.IO).launch {
            getLessonsInDay.execute(numDay = numDay).collectIndexed { i, value ->
                listLessons.postValue(value)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}