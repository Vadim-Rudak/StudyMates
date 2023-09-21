package com.vr.app.sh.ui.door.view

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.data.model.User
import com.vr.app.sh.data.repository.RegistrationInfo
import com.vr.app.sh.ui.base.AuthorizationViewModelFactory
import com.vr.app.sh.ui.door.viewmodel.AuthViewModel
import com.vr.app.sh.ui.menu.view.TopMenu
import com.vr.app.sh.ui.other.UseAlert
import com.vr.app.sh.ui.other.UseAlert.Companion.errorMessage
import com.vr.app.sh.ui.other.UseAlert.Companion.infoMessage
import com.vr.app.sh.ui.other.UseAlert.Companion.loading

class Authoriz : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: AuthorizationViewModelFactory

    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authoriz)

        val btn_in = findViewById<Button>(R.id.btn_in)
        val btn_reg = findViewById<TextView>(R.id.btn_reg)
        val login = findViewById<TextInputEditText>(R.id.TextUserName)
        val password = findViewById<TextInputEditText>(R.id.TextUserPassword)

        (applicationContext as App).appComponent.injectAuthoriz(this)

        viewModel = ViewModelProvider(this, factory)
            .get(AuthViewModel::class.java)

        viewModel.errorMessage.observe(this){
            infoMessage(supportFragmentManager,it)
        }

        viewModel.statusAuth.observe(this){
            val intent = Intent(this@Authoriz, TopMenu::class.java)
            startActivity(intent)
            finish()
        }

        btn_in.setOnClickListener{
            //check this
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)

            viewModel.authorization(this@Authoriz,supportFragmentManager,login.text.toString(),password.text.toString())
        }

        btn_reg.setOnClickListener {
            RegistrationInfo.user = User()
            val intent = Intent(this,Reg::class.java)
            startActivity(intent)
            login.setText("")
            password.setText("")
        }
    }
}