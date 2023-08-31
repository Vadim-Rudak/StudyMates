package com.vr.app.sh.ui.door.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
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
    private var num_fragment = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        val btnBack = findViewById<ImageButton>(R.id.reg_btn_back)
        btnBack.setOnClickListener {
            finish()
        }
        val titel = findViewById<TextView>(R.id.reg_text_titel)
        val tab1 = findViewById<ImageView>(R.id.reg_image_tab1)
        val tab2 = findViewById<ImageView>(R.id.reg_image_tab2)
        val tab3 = findViewById<ImageView>(R.id.reg_image_tab3)
        val tab4 = findViewById<ImageView>(R.id.reg_image_tab4)
        val btn_next = findViewById<Button>(R.id.reg_btn_next)

        (applicationContext as App).appComponent.injectReg(this)

        viewModel = ViewModelProvider(this,factory)
            .get(RegViewModel::class.java)

        viewModel.numFragment.observe(this){
            num_fragment = it
            when(it){
                1->{
                    titel.setText(R.string.registration_titel2)
                    tab2.setImageResource(R.drawable.tab_reg_true)
                }
                2->{
                    titel.setText(R.string.registration_titel3)
                    tab3.setImageResource(R.drawable.tab_reg_true)
                }
                3->{
                    titel.setText(R.string.registration_titel4)
                    tab4.setImageResource(R.drawable.tab_reg_true)
                    btn_next.setText(R.string.registration_btn2)
                }
                else->{
                    titel.setText(R.string.registration_titel1)
                    tab1.setImageResource(R.drawable.tab_reg_true)
                }
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fr_place, FragmentReg(numPage = it))
                .commit()
        }

        viewModel.errorMessage.observe(this){
            UseAlert.errorMessage(it,this)
        }

        viewModel.statusRegistration.observe(this){
            if(it){
                Toast.makeText(this, this.resources.getString(R.string.toastOkReg), Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        btn_next.setOnClickListener {
            if (num_fragment<3){
                num_fragment++
                viewModel.numFragment.postValue(num_fragment)
            }else{
                val intent = Intent(this,Verification::class.java)
                startActivity(intent)
                finish()
            }
        }

//        btn_send.setOnClickListener {
//            if (TextUtils.isEmpty(login.text.toString().trim())){
//                login.setText("")
//            }
//            if (TextUtils.isEmpty(password1.text.toString().trim())){
//                password1.setText("")
//            }
//            if (TextUtils.isEmpty(password2.text.toString().trim())){
//                password2.setText("")
//            }
//            viewModel.registration(login.text.toString(), password1.text.toString(),password2.text.toString(),
//            name.text.toString(),last_name.text.toString(),patronymic.text.toString(),date_birthday.text.toString(),
//            num_class.text.toString().toInt())
//        }
    }
}