package com.vr.app.sh.ui.door.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.RegViewModelFactory
import com.vr.app.sh.ui.door.viewmodel.RegViewModel
import com.vr.app.sh.ui.other.UseAlert

class Reg : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: RegViewModelFactory

    lateinit var viewModel: RegViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        val login = findViewById<EditText>(R.id.InputLogin)
        val password1 = findViewById<EditText>(R.id.InputPassword)
        val password2 = findViewById<EditText>(R.id.InputPassword2)
        val last_name = findViewById<EditText>(R.id.InputLastName)
        val name = findViewById<EditText>(R.id.InputName)
        val patronymic = findViewById<EditText>(R.id.InputPatronymic)
        val date_birthday = findViewById<EditText>(R.id.InputDateBirthday)
        val num_class = findViewById<EditText>(R.id.InputNumClass)
        val btn_send = findViewById<Button>(R.id.BtnReg)

        (applicationContext as App).appComponent.injectReg(this)

        viewModel = ViewModelProvider(this,factory)
            .get(RegViewModel::class.java)

        viewModel.errorMessage.observe(this){
            UseAlert.errorMessage(it,this)
        }

        viewModel.statusRegistration.observe(this){
            if(it){
                Toast.makeText(this, this.resources.getString(R.string.toastOkReg), Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        btn_send.setOnClickListener {
            if (TextUtils.isEmpty(login.text.toString().trim())){
                login.setText("")
            }
            if (TextUtils.isEmpty(password1.text.toString().trim())){
                password1.setText("")
            }
            if (TextUtils.isEmpty(password2.text.toString().trim())){
                password2.setText("")
            }
            viewModel.registration(login.text.toString(), password1.text.toString(),password2.text.toString(),
            name.text.toString(),last_name.text.toString(),patronymic.text.toString(),date_birthday.text.toString(),
            num_class.text.toString().toInt())
        }
    }
}