package com.vr.app.sh.ui.menu.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.vr.app.sh.domain.UseCase.ClearUser

class SettingsViewModel(context: Context, private val clearUser: ClearUser):ViewModel() {

    private val PREFS_NAME = "cookie"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    @SuppressLint("ApplySharedPref")
    fun userOut(){
        sharedPref.edit().clear().commit()
        clearUser.execute()
    }
}