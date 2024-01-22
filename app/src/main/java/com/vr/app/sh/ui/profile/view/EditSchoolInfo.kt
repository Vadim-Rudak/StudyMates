package com.vr.app.sh.ui.profile.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.textfield.TextInputEditText
import com.vr.app.sh.R

class EditSchoolInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_school_info)

        val animationView = findViewById<LottieAnimationView>(R.id.animationView)
        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.anim_fade_in)
        animationView.apply {
            startAnimation(animationFadeIn)
            repeatCount = LottieDrawable.INFINITE
            repeatMode = LottieDrawable.RESTART
            setAnimation("edit_school.json")
        }.playAnimation()

        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
        val viewTitle = findViewById<TextView>(R.id.viewTitle)
        viewTitle.text = resources.getString(R.string.editProfileTitle3)

        val sharedPrefs = getSharedPreferences("user_info", Context.MODE_PRIVATE)

        val inputNameSchool = findViewById<TextInputEditText>(R.id.TextNameSchool)
        if (sharedPrefs.contains("school_name")){
            inputNameSchool.setText(sharedPrefs.getString("school_name",null))
        }
        val inputNameCity = findViewById<TextInputEditText>(R.id.TextCountry)
        if (sharedPrefs.contains("school_name_city")){
            inputNameCity.setText(sharedPrefs.getString("school_name_city",null))
        }
        val inputNumClass = findViewById<TextInputEditText>(R.id.textNumClass)
        if (sharedPrefs.contains("school_num_class")){
            inputNumClass.setText(sharedPrefs.getInt("school_num_class",0).toString())
        }
        val checkEndSchool = findViewById<CheckBox>(R.id.checkBoxEndSchool)
        if (sharedPrefs.contains("school_end")){
            checkEndSchool.isChecked = sharedPrefs.getBoolean("school_end",false)
        }
    }
}