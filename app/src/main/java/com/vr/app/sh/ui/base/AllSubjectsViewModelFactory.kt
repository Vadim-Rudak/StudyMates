package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.data.repository.InternetRepoImpl
import com.vr.app.sh.data.repository.TestsRepoImpl
import com.vr.app.sh.domain.UseCase.GetListTestsInternet
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveTestsInBD
import com.vr.app.sh.ui.books.viewmodel.AddBookViewModel
import com.vr.app.sh.ui.door.viewmodel.AuthViewModel
import com.vr.app.sh.ui.tests.viewmodel.AllSubjectsViewModel

class AllSubjectsViewModelFactory(private val internetRepoImpl: InternetRepoImpl,private val testsRepoImpl: TestsRepoImpl, context: Context): ViewModelProvider.Factory {

    val getListTestsInternet by lazy(LazyThreadSafetyMode.NONE) { GetListTestsInternet(internetRepoImpl) }
    val saveListTestsInBD by lazy(LazyThreadSafetyMode.NONE) { SaveTestsInBD(testsRepoImpl) }
    val internetConnection by lazy(LazyThreadSafetyMode.NONE) { InternetConnection(context) }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AllSubjectsViewModel::class.java)) {
            AllSubjectsViewModel(getListTestsInternet,saveListTestsInBD,internetConnection) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}