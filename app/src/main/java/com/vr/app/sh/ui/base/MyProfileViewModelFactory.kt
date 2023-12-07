package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.domain.UseCase.GetListQuestionsBD
import com.vr.app.sh.ui.profile.viewModel.MyProfileViewModel
import com.vr.app.sh.ui.tests.viewmodel.OpenTestViewModel

class MyProfileViewModelFactory(
    val context: Context
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MyProfileViewModel::class.java)) {
            MyProfileViewModel(context.resources) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}