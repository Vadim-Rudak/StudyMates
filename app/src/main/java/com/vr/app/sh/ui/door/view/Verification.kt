package com.vr.app.sh.ui.door.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.vr.app.sh.R

class Verification : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        val animationView = findViewById<LottieAnimationView>(R.id.verificationAnim)

        animationView.repeatCount = LottieDrawable.INFINITE
        animationView.repeatMode = LottieDrawable.RESTART
        animationView.setAnimation("animVerification.json")
        animationView.playAnimation()

    }

}