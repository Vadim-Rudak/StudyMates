package com.vr.app.sh.ui.door.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.RegViewModelFactory
import com.vr.app.sh.ui.door.viewmodel.RegViewModel
import com.vr.app.sh.ui.other.RegistrationInfo
import com.vr.app.sh.ui.other.UseAlert.Companion.infoMessage

class Reg : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: RegViewModelFactory

    lateinit var viewModel: RegViewModel
    private var numFragment = 0
    private val userReg = RegistrationInfo.user

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        val btnBack = findViewById<ImageButton>(R.id.reg_btn_back)
        btnBack.setOnClickListener {
            startActivity(Intent(this,Authoriz::class.java))
            finish()
        }
        val title = findViewById<TextView>(R.id.reg_text_titel)
        val tab1 = findViewById<ImageView>(R.id.reg_image_tab1)
        val tab2 = findViewById<ImageView>(R.id.reg_image_tab2)
        val tab3 = findViewById<ImageView>(R.id.reg_image_tab3)
        val tab4 = findViewById<ImageView>(R.id.reg_image_tab4)
        val btnNext = findViewById<Button>(R.id.reg_btn_next)

        (applicationContext as App).appComponent.injectReg(this)

        viewModel = ViewModelProvider(this,factory)
            .get(RegViewModel::class.java)

        viewModel.numFragment.observe(this){
            numFragment = it
            when(it){
                1->{
                    title.setText(R.string.registration_titel2)
                    tab2.setImageResource(R.drawable.tab_reg_true)
                }
                2->{
                    title.setText(R.string.registration_titel3)
                    tab3.setImageResource(R.drawable.tab_reg_true)
                }
                3->{
                    title.setText(R.string.registration_titel4)
                    tab4.setImageResource(R.drawable.tab_reg_true)
                    btnNext.setText(R.string.registration_btn2)
                }
                else->{
                    title.setText(R.string.registration_titel1)
                    tab1.setImageResource(R.drawable.tab_reg_true)
                }
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fr_place, FragmentReg(numPage = it))
                .commit()
        }

        viewModel.errorMessage.observe(this){
            infoMessage(supportFragmentManager,it)
        }

        viewModel.statusRegistration.observe(this){
            if(it){
                startActivity(Intent(this,Verification::class.java))
                finish()
            }
        }

        btnNext.setOnClickListener {
            when(numFragment){
                0->{
                    infoInEditText(
                        !userReg.name.isNullOrEmpty()
                        &&!userReg.lastName.isNullOrEmpty()
                        &&!userReg.dateBirthday.isNullOrEmpty()
                        &&!userReg.cityLive.isNullOrEmpty()
                    )
                }
                1->{
                    infoInEditText(
                        !userReg.school.nameCity.isNullOrEmpty()
                        &&!userReg.school.name.isNullOrEmpty()
                        &&userReg.school.numClass != 0
                    )
                }
                2->{
                    infoInEditText(!userReg.auth.login.isNullOrEmpty()&&!userReg.auth.password.isNullOrEmpty())
                }
                3->{
                    viewModel.registration(supportFragmentManager)
                }
            }
        }
    }

    private fun infoInEditText(allInfo:Boolean){
        if (allInfo){
            numFragment++
            viewModel.numFragment.postValue(numFragment)
        }else{
            infoMessage(supportFragmentManager,this.resources.getString(R.string.alrInfoText1))
        }
    }
}