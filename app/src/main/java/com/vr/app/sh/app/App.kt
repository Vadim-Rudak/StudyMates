package com.vr.app.sh.app

import android.app.Application
import com.vr.app.sh.di.AppComponent
import com.vr.app.sh.di.AppModule
import com.vr.app.sh.di.DaggerAppComponent

class App:Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()

    }
}