package com.vr.app.sh.ui.menu.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.MenuViewModelFactory
import com.vr.app.sh.ui.books.view.Books
import com.vr.app.sh.ui.menu.viewModel.MenuViewModel
import com.vr.app.sh.ui.tests.view.subjects.ActivitySubjects

class TopMenu : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: MenuViewModelFactory

    lateinit var viewModel: MenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_menu)

        val btn_book = findViewById<Button>(R.id.button8)
        val btn_test = findViewById<Button>(R.id.button5)
        val progressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar_menu)
        val text_progressBar = findViewById<TextView>(R.id.textProgress_menu)

        (applicationContext as App).appComponent.injectTopMenu(this)

        viewModel = ViewModelProvider(this, factory)
            .get(MenuViewModel::class.java)

        viewModel.statusListBook.observe(this){
            if (it){
                val intent = Intent(this@TopMenu, Books::class.java)
                startActivity(intent)
            }
        }

        viewModel.errorMessage.observe(this){
            viewModel.errorMessage(it,this)
        }

        viewModel.loading.observe(this){
            if (it){
                btn_book.visibility = View.GONE
                btn_test.visibility = View.GONE
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
                btn_book.visibility = View.VISIBLE
                btn_test.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                text_progressBar.visibility = View.GONE
            }
        }

        btn_book.setOnClickListener {
            if(isStoragePermissionGranted()){
                viewModel.getAllBooks()
            }
        }

        btn_test.setOnClickListener {
            val intent = Intent(this,ActivitySubjects::class.java)
            startActivity(intent)
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