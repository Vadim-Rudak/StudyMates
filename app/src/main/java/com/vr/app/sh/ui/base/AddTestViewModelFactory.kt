package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.data.repository.BookRepoImpl
import com.vr.app.sh.data.repository.InternetRepoImpl
import com.vr.app.sh.data.repository.TestsRepoImpl
import com.vr.app.sh.data.repository.UserRepoImpl
import com.vr.app.sh.domain.UseCase.*
import com.vr.app.sh.domain.repository.TestsInternetRepo
import com.vr.app.sh.ui.books.viewmodel.AddBookViewModel
import com.vr.app.sh.ui.door.viewmodel.AuthViewModel
import com.vr.app.sh.ui.tests.viewmodel.AddTestViewModel

class AddTestViewModelFactory(private val testsRepoImpl: TestsRepoImpl,private val internetRepoImpl: InternetRepoImpl, context: Context): ViewModelProvider.Factory {

    val saveTestInBD by lazy(LazyThreadSafetyMode.NONE) { SaveTestsInBD(testsRepoImpl) }
    val getInfoTests by lazy(LazyThreadSafetyMode.NONE) { GetListTestsInternet(internetRepoImpl) }
    val sendTest by lazy(LazyThreadSafetyMode.NONE) { SendTestInfo(internetRepoImpl) }
    val sendQuestions by lazy(LazyThreadSafetyMode.NONE) { SendQuestions(internetRepoImpl) }
    val internetConnection by lazy(LazyThreadSafetyMode.NONE) { InternetConnection(context) }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AddTestViewModel::class.java)) {
            AddTestViewModel(getInfoTests,saveTestInBD,sendTest,sendQuestions,internetConnection) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}