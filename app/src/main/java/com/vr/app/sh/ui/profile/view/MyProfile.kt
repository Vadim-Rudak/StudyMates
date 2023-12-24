package com.vr.app.sh.ui.profile.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.WindowCompat
import androidx.core.view.doOnLayout
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.MyProfileViewModelFactory
import com.vr.app.sh.ui.profile.viewModel.MyProfileViewModel
import dev.chrisbanes.insetter.applyInsetter
import java.io.File

class MyProfile : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: MyProfileViewModelFactory

    lateinit var viewModel: MyProfileViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        WindowCompat.setDecorFitsSystemWindows(window,false)
        window.statusBarColor = Color.TRANSPARENT

        (applicationContext as App).appComponent.injectMyProfile(this)

        viewModel = ViewModelProvider(this, factory)
            .get(MyProfileViewModel::class.java)

        val viewBottomSheet = findViewById<LinearLayout>(R.id.btn_sheet_profile_info)
        viewModel.createBottomSheet(viewBottomSheet)

        val sharedPrefs = getSharedPreferences("user_info", Context.MODE_PRIVATE)

        val viewUserPhoto = findViewById<ImageView>(R.id.user_photo)
        if (sharedPrefs.contains("photo_name")){
            val pathMyPhoto = "${Environment.getExternalStorageDirectory().path}/SchoolProg/MyProfile/${sharedPrefs.getString("photo_name",null)}"
            if (File(pathMyPhoto).exists()){
                viewUserPhoto.setImageURI(Uri.fromFile(File(pathMyPhoto)))
            }else{
                //Download Photo from server

            }
        }

        val btnEditProfile = viewBottomSheet.findViewById<ImageButton>(R.id.btn_edit_profile)
        btnEditProfile.setOnClickListener{
            startActivity(Intent(this,EditProfile::class.java))
        }
        val viewUserName = viewBottomSheet.findViewById<TextView>(R.id.profile_name)
        if (sharedPrefs.contains("user_name")&&sharedPrefs.contains("user_last_name")){
            viewUserName.text = "${sharedPrefs.getString("user_name",null)} ${sharedPrefs.getString("user_last_name",null)}"
        }
        val viewUserRole = viewBottomSheet.findViewById<TextView>(R.id.profile_role)
        if (sharedPrefs.contains("role")){
            when(sharedPrefs.getString("role",null)){
                "USER"->{viewUserRole.text = this.resources.getStringArray(R.array.roles)[0]}
                "TEACHER"->{viewUserRole.text = this.resources.getStringArray(R.array.roles)[1]}
                "ADMIN"->{viewUserRole.text = this.resources.getStringArray(R.array.roles)[2]}
            }
        }
        val viewUserLocate = viewBottomSheet.findViewById<TextView>(R.id.profile_locate)
        if (sharedPrefs.contains("user_city_live")){
            viewUserLocate.text = sharedPrefs.getString("user_city_live",null)
        }
        val viewUserDateBirthday = viewBottomSheet.findViewById<TextView>(R.id.profile_date_birthday)
        if (sharedPrefs.contains("user_date_bithday")){
            viewUserDateBirthday.text = sharedPrefs.getString("user_date_bithday",null)
        }
        val viewUserClass = viewBottomSheet.findViewById<TextView>(R.id.profile_num_class)
        if (sharedPrefs.contains("school_num_class")){
            viewUserClass.text = "${ sharedPrefs.getInt("school_num_class", 0) } ${this.resources.getString(R.string.profile_text_num_class)}"
        }

        val btnBack = findViewById<ImageButton>(R.id.myProfileBtnBack)
        btnBack.setOnClickListener {
            finish()
        }


        val backgroundView = findViewById<CoordinatorLayout>(R.id.backgroundView)
        backgroundView.doOnLayout {
            viewModel.setBottomSheetMaxHeight(it.measuredHeight)
        }

        backgroundView.applyInsetter {
            type(navigationBars = true){
                margin()
            }
        }

        val bgViewBottom = findViewById<View>(R.id.bg_view_bottom)
        bgViewBottom.doOnLayout {
            viewModel.setBottomSheetDefaultHeight(it.height)
        }

        viewModel.heightBottomSheet.observe(this){
            if(viewBottomSheet.layoutParams.height<it){
                viewBottomSheet.layoutParams.height = it
            }
            viewModel.seeBottomSheet(it)
        }
    }
}