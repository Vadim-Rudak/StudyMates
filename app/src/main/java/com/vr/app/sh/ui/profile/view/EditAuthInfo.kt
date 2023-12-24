package com.vr.app.sh.ui.profile.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.textfield.TextInputEditText
import com.vr.app.sh.R

class EditAuthInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_auth_info)

        val animationView = findViewById<LottieAnimationView>(R.id.animationView)

        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.anim_fade_in)
        animationView.startAnimation(animationFadeIn)

        animationView.repeatCount = LottieDrawable.INFINITE
        animationView.repeatMode = LottieDrawable.RESTART
        animationView.setAnimation("edit_auth_anim.json")
        animationView.playAnimation()

        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
        val viewTitle = findViewById<TextView>(R.id.viewTitle)
        viewTitle.text = resources.getString(R.string.editProfileTitle2)

        val sharedPrefs = getSharedPreferences("user_info", Context.MODE_PRIVATE)

        val inpLogin = findViewById<TextInputEditText>(R.id.inpLogin)
        if (sharedPrefs.contains("login")){
            inpLogin.setText(sharedPrefs.getString("login",null))
        }
    }
}