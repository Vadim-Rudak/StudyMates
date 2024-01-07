package com.vr.app.sh.ui.tests.viewmodel

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.R
import com.vr.app.sh.domain.UseCase.GetListTestsInternet
import com.vr.app.sh.domain.UseCase.SaveTestsInBD
import com.vr.app.sh.ui.tests.adapter.BtnSubjectAdapter
import com.vr.app.sh.ui.tests.adapter.SubjectItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllSubjectsViewModel(private val resources: Resources,val getListTestsInternet: GetListTestsInternet,val saveTestsInBD: SaveTestsInBD,private val internetConnect:Boolean): ViewModel() {

    var statusTestsInBD = MutableLiveData<Boolean>()
    val sub = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
    val adapter = BtnSubjectAdapter(resources)
    val decoration = SubjectItemDecoration(resources)
    var job: Job? = null


    fun getAllTests(subject:String){
        if (internetConnect){
            sub.value = subject
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main){
                    getListTestsInternet.execute(subject).also {
                        if (it.success){
                            it.list?.let { it1 -> saveTestsInBD.execute(it1) }
                            statusTestsInBD.value = true
                        }else{
                            errorMessage.value = resources.getString(R.string.alrErrorGetData)
                        }
                    }
                }
            }
        }else{
            errorMessage.value = resources.getString(R.string.alrNotInternetConnection)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
