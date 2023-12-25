package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetListTestsInternet
import com.vr.app.sh.domain.UseCase.SaveTestsInBD
import com.vr.app.sh.ui.other.InternetConnection
import com.vr.app.sh.ui.tests.viewmodel.AllSubjectsViewModel

class AllSubjectsViewModelFactory(
    val context: Context,
    val getListTestsInternet: GetListTestsInternet,
    val saveListTestsInBD: SaveTestsInBD
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AllSubjectsViewModel::class.java)) {
            AllSubjectsViewModel(
                resources = context.resources,
                getListTestsInternet = getListTestsInternet,
                saveTestsInBD = saveListTestsInBD,
                internetConnect = InternetConnection.useInternet(context)
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}