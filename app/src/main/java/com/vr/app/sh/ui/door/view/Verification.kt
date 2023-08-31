package com.vr.app.sh.ui.door.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.vr.app.sh.R
import com.vr.app.sh.ui.menu.view.TopMenu

class Verification : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        val animationView = findViewById<LottieAnimationView>(R.id.verificationAnim)

        animationView.repeatCount = LottieDrawable.INFINITE
        animationView.repeatMode = LottieDrawable.RESTART
        animationView.setAnimation("animVerification.json")
        animationView.playAnimation()

        val btnTakePhoto = findViewById<Button>(R.id.verification_btn_take_photo)
        val btnLate = findViewById<Button>(R.id.verification_btn_late)

        btnTakePhoto.setOnClickListener {

        }

        btnLate.setOnClickListener {
            val intent = Intent(this,TopMenu::class.java)
            startActivity(intent)
            finish()
        }

    }

}