package com.vr.app.sh.ui.tests.viewmodel

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.R
import com.vr.app.sh.domain.UseCase.SendResult
import com.vr.app.sh.domain.model.ResultTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultViewModel(
    private val resources: Resources,
    private val sendResult: SendResult,
    private val internetConnect:Boolean
): ViewModel() {

    val status_send = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null

    fun sendResult(result: ResultTest){
        if (internetConnect){
            job = CoroutineScope(Dispatchers.IO).launch {
                val response = sendResult.execute(result)

                withContext(Dispatchers.Main) {
                    if (response.success) {
                        status_send.value = true
                    } else {
                        errorMessage.value = resources.getString(R.string.alrErrorSendResultTest)
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