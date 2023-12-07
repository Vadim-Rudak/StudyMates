package com.vr.app.sh.ui.menu.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.MenuViewModelFactory
import com.vr.app.sh.ui.base.SettingsViewModelFactory
import com.vr.app.sh.ui.menu.viewModel.MenuViewModel
import com.vr.app.sh.ui.menu.viewModel.SettingsViewModel

class Settings : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: SettingsViewModelFactory

    lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        (applicationContext as App).appComponent.injectSettings(this)

        viewModel = ViewModelProvider(this, factory)
            .get(SettingsViewModel::class.java)

        val btnBack = findViewById<ImageButton>(R.id.settings_btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        val btnExitUser = findViewById<Button>(R.id.btnExitUser)
        btnExitUser.setOnClickListener {
            viewModel.userOut()
        }
    }
}