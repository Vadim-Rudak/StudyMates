package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetListTestsInternet
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveTestsInBD
import com.vr.app.sh.ui.tests.viewmodel.AllSubjectsViewModel

class AllSubjectsViewModelFactory(
    val context: Context,
    val getListTestsInternet: GetListTestsInternet,
    val saveListTestsInBD: SaveTestsInBD,
    val internetConnection: InternetConnection
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AllSubjectsViewModel::class.java)) {
            AllSubjectsViewModel(context.resources,getListTestsInternet,saveListTestsInBD,internetConnection) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}