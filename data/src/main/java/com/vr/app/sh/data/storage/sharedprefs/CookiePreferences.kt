package com.vr.app.sh.data.storage.sharedprefs

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vr.app.sh.domain.repository.local.CookieRepo
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class CookiePreferences(context: Context) : CookieJar, CookieRepo {

    private val PREFS_NAME = "cookie"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private var cookies: List<Cookie>? = null


    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        cookies = if (sharedPref.contains("cookie")){
            val itemType = object : TypeToken<List<Cookie>>() {}.type
            val listInString = sharedPref.getString("cookie",null)
            Gson().fromJson(listInString,itemType)
        }else{
            null
        }
        return cookies ?: ArrayList()
    }

    @SuppressLint("CommitPrefEdits")
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val editor = sharedPref.edit()
        val arrayInString = Gson().toJson(cookies)
        editor.putString("cookie",arrayInString)
        editor!!.commit()
    }

    @SuppressLint("ApplySharedPref")
    override fun clean() {
        sharedPref.edit().clear().commit()
    }
}