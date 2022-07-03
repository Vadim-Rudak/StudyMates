package com.vr.app.sh.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.data.repository.InternetRepoImpl
import com.vr.app.sh.data.repository.UserRepoImpl
import com.vr.app.sh.domain.UseCase.Authorization
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.Registration
import com.vr.app.sh.domain.UseCase.SetUserInBD
import com.vr.app.sh.ui.door.viewmodel.AuthViewModel
import com.vr.app.sh.ui.door.viewmodel.RegViewModel

class AuthorizationViewModelFactory(private val internetRepoImpl: InternetRepoImpl,private val userRepoImpl: UserRepoImpl,context: Context): ViewModelProvider.Factory  {

    val authorization by lazy(LazyThreadSafetyMode.NONE) { Authorization(internetRepoImpl) }
    val setUserInBD by lazy(LazyThreadSafetyMode.NONE) { SetUserInBD(userRepoImpl) }
    val internetConnection by lazy(LazyThreadSafetyMode.NONE) { InternetConnection(context) }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            AuthViewModel(authorization,setUserInBD,internetConnection) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}