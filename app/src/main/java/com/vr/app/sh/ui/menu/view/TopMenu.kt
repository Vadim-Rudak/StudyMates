package com.vr.app.sh.ui.menu.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.data.repository.RegistrationInfo
import com.vr.app.sh.ui.base.MenuViewModelFactory
import com.vr.app.sh.ui.books.view.Books
import com.vr.app.sh.ui.menu.adapter.MenuItemDecoration
import com.vr.app.sh.ui.menu.adapter.TopMenuAdapter
import com.vr.app.sh.ui.menu.viewModel.MenuViewModel
import com.vr.app.sh.ui.other.UseAlert.Companion.errorMessage
import com.vr.app.sh.ui.profile.view.MyProfile
import com.vr.app.sh.ui.tests.view.subjects.ActivitySubjects
import com.vr.app.sh.ui.timeTable.view.TimeTable
import java.io.File

class TopMenu : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: MenuViewModelFactory

    lateinit var viewModel: MenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_menu)

        (applicationContext as App).appComponent.injectTopMenu(this)

        viewModel = ViewModelProvider(this, factory)
            .get(MenuViewModel::class.java)


        val sharedPrefs = getSharedPreferences("user_info", Context.MODE_PRIVATE)

        val myPhoto = findViewById<ShapeableImageView>(R.id.topMenuUserPhoto)
        if (sharedPrefs.contains("photo_name")){
            val pathMyPhoto = "${Environment.getExternalStorageDirectory().path}/SchoolProg/MyProfile/${sharedPrefs.getString("photo_name",null)}"
            if (File(pathMyPhoto).exists()){
                Glide.with(this)
                    .load(pathMyPhoto)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(myPhoto)
            }else{
                //Download Photo from server

            }
        }

        val viewNameUser = findViewById<TextView>(R.id.topMenuUserName)
        if(sharedPrefs.contains("user_name")){
            viewNameUser.text = sharedPrefs.getString("user_name"," ")
        }
        val listener = SharedPreferences.OnSharedPreferenceChangeListener{sharedPreferences, key ->
            if (key == "user_name"){
                viewNameUser.text = sharedPreferences.getString("user_name"," ")
            }
        }
        sharedPrefs.registerOnSharedPreferenceChangeListener(listener)

        val btnSettings = findViewById<ImageButton>(R.id.btnSettings)
        btnSettings.setOnClickListener {
            val intent = Intent(this,Settings::class.java)
            startActivity(intent)
        }
        
        viewModel.errorMessage.observe(this){
            errorMessage(it,this)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerTopMenu)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = viewModel.adapter

        recyclerView.addItemDecoration(MenuItemDecoration(this))
        viewModel.adapter.setListener(object : TopMenuAdapter.Listener{
            override fun onClick(position: Int) {
                when(position){
                    0->{
                        if(isStoragePermissionGranted()){
                            viewModel.getAllBooks()
                        }
                    }
                    1->{
                        val intent = Intent(this@TopMenu, ActivitySubjects::class.java)
                        startActivity(intent)

                    }
                    2->{
                        val intent = Intent(this@TopMenu, TimeTable::class.java)
                        startActivity(intent)
                    }
                }
            }

        })

        val navMenu = findViewById<LinearLayout>(R.id.navMenu)
        val linearLayout = findViewById<LinearLayout>(R.id.progressLayout)
        val progressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar_menu)
        val text_progressBar = findViewById<TextView>(R.id.textProgress_menu)

        navMenu.setOnClickListener {
            val intent = Intent(this,MyProfile::class.java)
            startActivity(intent)
        }

        viewModel.statusListBook.observe(this){
            if (it){
                val intent = Intent(this@TopMenu, Books::class.java)
                startActivity(intent)
            }
        }

        viewModel.loading.observe(this){
            if (it){
                navMenu.visibility = View.GONE
                recyclerView.visibility = View.GONE
                linearLayout.visibility = View.VISIBLE
                text_progressBar.visibility = View.VISIBLE
                progressBar.visibility = View.VISIBLE
                progressBar.apply {
                    progressBarWidth = 14f
                    backgroundProgressBarWidth = 2f
                    backgroundProgressBarColor = Color.parseColor("#969696")
                    progressBarColorStart = Color.parseColor("#6767bb")
                    progressBarColorEnd = Color.parseColor("#4c7daf")
                    roundBorder = true
                    indeterminateMode = true
                }
            }else{
                navMenu.visibility = View.VISIBLE
                recyclerView.visibility = View.VISIBLE
                linearLayout.visibility = View.GONE
                progressBar.visibility = View.GONE
                text_progressBar.visibility = View.GONE
            }
        }

    }

    fun isStoragePermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
                false
            }
        } else {
            true
        }
    }
}