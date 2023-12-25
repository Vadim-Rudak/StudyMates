package com.vr.app.sh.data.storage.sharedprefs

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.vr.app.sh.domain.model.*
import com.vr.app.sh.domain.repository.local.UserRepo

class UserPreferences(context:Context): UserRepo {

    private val PREFS_NAME = "user_info"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    @SuppressLint("CommitPrefEdits")
    override fun save(user: User) {
        val editor = sharedPref.edit()

        editor.putInt("id", user.id)
        editor.putString("user_name", user.name)
        editor.putString("user_last_name", user.lastName)
        editor.putString("user_gender", user.gender)
        editor.putString("user_date_bithday", user.dateBirthday)
        editor.putString("user_city_live", user.cityLive)

        editor.putBoolean("auth_active",user.auth.active)
        editor.putString("login", user.auth.login)
        editor.putString("password", user.auth.password)
        editor.putString("role", user.auth.role)

        editor.putString("school_name", user.school.name)
        editor.putString("school_name_city", user.school.nameCity)
        editor.putInt("school_num_class", user.school.numClass)
        editor.putBoolean("school_end", user.school.endSchool)

        editor.putBoolean("verification", user.photo.verification)
        editor.putString("photo_name",user.photo.name)
        editor.putString("photo_path", user.photo.path)

        editor!!.commit()
    }

    @SuppressLint("ApplySharedPref")
    override fun clear() {
        val editor = sharedPref.edit()
        editor.clear()
        editor.commit()
    }
}