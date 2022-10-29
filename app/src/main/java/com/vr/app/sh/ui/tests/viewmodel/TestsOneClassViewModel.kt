package com.vr.app.sh.ui.tests.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.domain.UseCase.GetListQuestions
import com.vr.app.sh.domain.UseCase.GetListTestsInClass
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveQuestionsInBD
import com.vr.app.sh.ui.tests.adapter.BtnTestAdapter
import kotlinx.coroutines.*

class TestsOneClassViewModel(val getListTestsInClass: GetListTestsInClass, val getListQuestions: GetListQuestions,val saveQuestionsInBD: SaveQuestionsInBD, val internetConnection: InternetConnection, val num_class: Int): ViewModel() {

    lateinit var name_test:String
    val adapter = BtnTestAdapter()
    val errorMessage = MutableLiveData<String>()
    val openTest = MutableLiveData<Boolean>()
    var job: Job? = null

    init {
        updateAdapter()
    }

    fun updateAdapter(){
        adapter.setTests(getListTestsInClass.execute(num_class))
        adapter.notifyDataSetChanged()
    }

    fun errorMessage(textMessage:String,context: Context){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Ошибка")
        alertDialog.setMessage(textMessage)
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
        alertDialog.show()
    }

    fun saveQuestionsInDB(test_id:Int,nameTest:String){
        if (internetConnection.UseInternet()){
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main){
                    val listQuestions = getListQuestions.execute(test_id)
                    name_test = nameTest
                    if (listQuestions.isSuccessful){
                        saveQuestionsInBD.execute(listQuestions.body()!!)
                        if (listQuestions.body()!!.isNotEmpty()){
                            openTest.value = true
                        }else{
                            errorMessage.value = "В тесте нет вопросов"
                        }
                    }else{
                        errorMessage.value = "Не удалось получить информацию о вопросах"
                    }
                }
            }
        }else{
           errorMessage.value = "Нет подключения к интернету"
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}