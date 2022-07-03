package com.vr.app.sh.ui.tests.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.data.api.NetworkService
import com.vr.app.sh.domain.UseCase.GetListTestsInternet
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveTestsInBD
import com.vr.app.sh.ui.tests.view.listTests.WindowTestsNames
import kotlinx.coroutines.*

class AllSubjectsViewModel(val getListTestsInternet: GetListTestsInternet,val saveTestsInBD: SaveTestsInBD,val internetConnection: InternetConnection): ViewModel() {

    var statusTestsInBD = MutableLiveData<Boolean>()
    val sub = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null

    fun errorMessage(textMessage:String,context: Context){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Ошибка")
        alertDialog.setMessage(textMessage)
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
        alertDialog.show()
    }

    fun getAllTests(subject:String){
        if (internetConnection.UseInternet()){
            sub.value = subject
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main){
                    Log.d("FFF","В бд")
                    val ListTests = getListTestsInternet.execute(subject)

                    if (ListTests.isSuccessful){
                        saveTestsInBD.execute(ListTests.body()!!)
                        statusTestsInBD.value = true
                    }else{
                        errorMessage.value = "Не удалось получить данные"
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
