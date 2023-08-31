package com.vr.app.sh.ui.door.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.vr.app.sh.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Splash : AppCompatActivity() {

    var job: Job?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val animationView = findViewById<LottieAnimationView>(R.id.animationView)

        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.anim_fade_in)
        animationView.startAnimation(animationFadeIn)

        animationView.repeatCount = LottieDrawable.INFINITE
        animationView.repeatMode = LottieDrawable.RESTART
        animationView.setAnimation("animSplashScreen.json")
        animationView.playAnimation()

        val intent = Intent(this,Authoriz::class.java)

        job = CoroutineScope(Dispatchers.IO).launch {
            delay(1600)
            startActivity(intent)
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}