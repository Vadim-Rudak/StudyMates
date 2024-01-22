package com.vr.app.sh.ui.profile.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.vr.app.sh.R
import java.io.File

class EditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
        val viewTitle = findViewById<TextView>(R.id.viewTitle)
        viewTitle.text = resources.getString(R.string.editProfileTitle)
        val btnEditPassword = findViewById<ImageButton>(R.id.btn_edit_password)
        btnEditPassword.setOnClickListener {
            startActivity(Intent(this,EditAuthInfo::class.java))
        }
        val btnEditSchool = findViewById<ImageButton>(R.id.btn_edit_school)
        btnEditSchool.setOnClickListener {
            startActivity(Intent(this,EditSchoolInfo::class.java))
        }

        val sharedPrefs = getSharedPreferences("user_info", Context.MODE_PRIVATE)

        val editUserPhoto = findViewById<ShapeableImageView>(R.id.editUserPhoto)
        if (sharedPrefs.contains("photo_name")){
            val pathMyPhoto = "${Environment.getExternalStorageDirectory().path}/SchoolProg/MyProfile/${sharedPrefs.getString("photo_name",null)}"
            if (File(pathMyPhoto).exists()){
                editUserPhoto.setImageURI(Uri.fromFile(File(pathMyPhoto)))
            }else{
                //Download Photo from server

            }
        }
        val userName = findViewById<TextView>(R.id.user_name)
        if (sharedPrefs.contains("user_name")&&sharedPrefs.contains("user_last_name")){
            userName.text = "${sharedPrefs.getString("user_name",null)} ${sharedPrefs.getString("user_last_name",null)}"
        }
        val inputName = findViewById<TextInputEditText>(R.id.TextUserName)
        if(sharedPrefs.contains("user_name")){
            inputName.setText(sharedPrefs.getString("user_name"," "))
        }
        val inputLastName = findViewById<TextInputEditText>(R.id.TextUserLastName)
        if(sharedPrefs.contains("user_last_name")){
            inputLastName.setText(sharedPrefs.getString("user_last_name"," "))
        }
        val inputDateBirthday = findViewById<TextInputEditText>(R.id.TextUserDateBithday)
        if (sharedPrefs.contains("user_date_bithday")){
            inputDateBirthday.setText(sharedPrefs.getString("user_date_bithday",null))
        }
        val inputCityLive = findViewById<TextInputEditText>(R.id.TextCityLive)
        if (sharedPrefs.contains("user_city_live")){
            inputCityLive.setText(sharedPrefs.getString("user_city_live",null))
        }
    }
}