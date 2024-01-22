package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.*
import com.vr.app.sh.ui.other.InternetConnection
import com.vr.app.sh.ui.tests.viewmodel.AddTestViewModel

class AddTestViewModelFactory(
    val context: Context,
    private val getInfoTests: GetListTestsInternet,
    private val saveTestInBD: SaveTestsInBD,
    private val sendTest: SendTestInfo,
    private val sendQuestions: SendQuestions
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AddTestViewModel::class.java)) {
            AddTestViewModel(
                context = context,
                getListTestsInternet = getInfoTests,
                saveTestsInBD = saveTestInBD,
                sendTestInfo = sendTest,
                sendQuestions = sendQuestions,
                internetConnect = InternetConnection.useInternet(context)
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}