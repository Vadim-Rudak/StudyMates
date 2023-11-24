package com.vr.app.sh.ui.door.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.view.PreviewView
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.VerificationViewModelFactory
import com.vr.app.sh.ui.door.viewmodel.VerificationViewModel
import com.vr.app.sh.ui.menu.view.TopMenu
import com.vr.app.sh.ui.other.UseAlert.Companion.verificationMsg
import java.io.File


class Verification : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: VerificationViewModelFactory

    lateinit var viewModel: VerificationViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        (applicationContext as App).appComponent.injectVerification(this)

        viewModel = ViewModelProvider(this,factory)
            .get(VerificationViewModel::class.java)

        val animationView = findViewById<LottieAnimationView>(R.id.verificationAnim)
        viewModel.setAnim(animationView)

        val viewFinder = findViewById<PreviewView>(R.id.viewFinder)
        viewFinder.scaleType = PreviewView.ScaleType.FILL_CENTER
        val btnTakePhoto = findViewById<Button>(R.id.verification_btn_take_photo)
        val btnLate = findViewById<Button>(R.id.verification_btn_late)

        viewModel.outputDirectory = getOutputDirectory()
        val sharedPrefs = getSharedPreferences("user_info", Context.MODE_PRIVATE)

        viewModel.openCamera.observe(this){
            when(it){
                0->{
                    animationView.visibility = View.VISIBLE
                    viewFinder.visibility = View.GONE
                }
                1->{
                    animationView.visibility = View.GONE
                    viewFinder.visibility = View.VISIBLE
                    viewModel.startCamera(viewFinder,this)
                    btnTakePhoto.text = getText(R.string.win_verification_btn1_1)
                }
            }
        }

        viewModel.status_verification.observe(this){
            if(it){
                val intent = Intent(this,TopMenu::class.java)
                intent.putExtra("verification",true)
                startActivity(intent)
                finish()
            }else{
                verificationMsg(
                    nameAnim = "verification_error.json",
                    textTitel = this.resources.getString(R.string.win_verification_info_msg_t2),
                    textMessage = this.resources.getString(R.string.win_verification_info_msg_info2)
                ).show(supportFragmentManager,"verification alert")
            }
        }

        btnTakePhoto.setOnClickListener {
            when(viewModel.openCamera.value){
                0->{
                    viewModel.openCamera.postValue(1)
                }
                else->{
                    viewModel.takePhoto(supportFragmentManager,sharedPrefs.getInt("id",0))
                }
            }
        }

        btnLate.setOnClickListener {
            val intent = Intent(this,TopMenu::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getOutputDirectory():File{
        val mediaDir = externalMediaDirs.firstOrNull()?.let {mFile ->
            File(mFile, resources.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }

        return if(mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }
}