package com.vr.app.sh.ui.tests.viewmodel

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.R
import com.vr.app.sh.domain.UseCase.GetListQuestions
import com.vr.app.sh.domain.UseCase.GetListTestsInClass
import com.vr.app.sh.domain.UseCase.SaveQuestionsInBD
import com.vr.app.sh.domain.model.Test
import com.vr.app.sh.ui.tests.adapter.BtnTestAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TestsOneClassViewModel(
    private val resources: Resources,
    private val getListTestsInClass: GetListTestsInClass,
    val getListQuestions: GetListQuestions,
    val saveQuestionsInBD: SaveQuestionsInBD,
    val numClass: Int,
    private val internetConnect:Boolean
    ): ViewModel() {

    lateinit var name_test:String
    val adapter = BtnTestAdapter()
    val errorMessage = MutableLiveData<String>()
    val openTest = MutableLiveData<Boolean>()
    val listTests = MutableLiveData<List<Test>>()
    var job: Job? = null

    init {
        fetchTests()
    }

    private fun fetchTests () {
        job = CoroutineScope(Dispatchers.IO).launch {
            getListTestsInClass.execute(numClass).collectIndexed { _, value ->
                listTests.postValue(value)
            }
        }
    }

    fun saveQuestionsInDB(test_id:Int,nameTest:String){
        if (internetConnect){
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main){
                    name_test = nameTest
                    getListQuestions.execute(test_id).also {
                        if (it.success){
                            it.list?.let { it1 -> saveQuestionsInBD.execute(it1) }
                            if (it.list!!.isNotEmpty()){
                                openTest.value = true
                            }else{
                                errorMessage.value = resources.getString(R.string.alrErrorNotQuestions)
                            }
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