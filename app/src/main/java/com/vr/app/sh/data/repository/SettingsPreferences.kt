package com.vr.app.sh.data.repository

import android.content.Context
import android.content.SharedPreferences

class SettingsPreferences(context: Context) {

    private val PREFS_NAME = "settings"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

}