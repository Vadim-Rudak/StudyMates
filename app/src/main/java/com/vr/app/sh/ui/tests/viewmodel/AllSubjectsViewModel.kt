package com.vr.app.sh.ui.tests.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.R
import com.vr.app.sh.domain.UseCase.GetListTestsInternet
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveTestsInBD
import kotlinx.coroutines.*

class AllSubjectsViewModel(private val resources: Resources,val getListTestsInternet: GetListTestsInternet,val saveTestsInBD: SaveTestsInBD,val internetConnection: InternetConnection): ViewModel() {

    var statusTestsInBD = MutableLiveData<Boolean>()
    val sub = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null

    fun getAllTests(subject:String){
        if (internetConnection.UseInternet()){
            sub.value = subject
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main){
                    val ListTests = getListTestsInternet.execute(subject)

                    if (ListTests.isSuccessful){
                        saveTestsInBD.execute(ListTests.body()!!)
                        statusTestsInBD.value = true
                    }else{
                        errorMessage.value = resources.getString(R.string.alrErrorGetData)
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
