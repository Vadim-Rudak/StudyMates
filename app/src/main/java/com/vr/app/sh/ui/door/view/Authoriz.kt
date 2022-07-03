package com.vr.app.sh.ui.door.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.R
import com.vr.app.sh.data.api.NetworkService
import com.vr.app.sh.data.repository.InternetRepoImpl
import com.vr.app.sh.data.repository.UserRepoImpl
import com.vr.app.sh.ui.base.AuthorizationViewModelFactory
import com.vr.app.sh.ui.door.viewmodel.AuthViewModel
import com.vr.app.sh.ui.menu.view.TopMenu

class Authoriz : AppCompatActivity() {

    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authoriz)

        val btn_in = findViewById<Button>(R.id.btn_in)
        val btn_reg = findViewById<Button>(R.id.btn_reg)
        val login = findViewById<EditText>(R.id.TextUserName)
        val password = findViewById<EditText>(R.id.TextUserPassword)

        val retrofitService = NetworkService.getInstance()
        val mainRepository = InternetRepoImpl(retrofitService)
        val userRepoImpl = UserRepoImpl(this)
        viewModel = ViewModelProvider(this, AuthorizationViewModelFactory(mainRepository,userRepoImpl,this))
            .get(AuthViewModel::class.java)

        viewModel.errorMessage.observe(this){
            viewModel.errorMessage(it,this)
            Log.d("FFF",it)
        }

        viewModel.statusAuth.observe(this){
            val intent = Intent(this@Authoriz, TopMenu::class.java)
            startActivity(intent)
        }

        btn_in.setOnClickListener{
            if (TextUtils.isEmpty(login.text.toString().trim())){
                login.setText("")
            }
            if (TextUtils.isEmpty(password.text.toString().trim())){
                password.setText("")
            }
            viewModel.authorization(login.text.toString(),password.text.toString())
        }

        btn_reg.setOnClickListener {
            val intent = Intent(this,Reg::class.java)
            startActivity(intent)
            login.setText("")
            password.setText("")
        }
    }
}