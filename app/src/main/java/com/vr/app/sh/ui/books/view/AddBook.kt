package com.vr.app.sh.ui.books.view

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.AddBookViewModelFactory
import com.vr.app.sh.ui.books.viewmodel.AddBookViewModel
import com.vr.app.sh.ui.other.UseAlert
import kotlinx.coroutines.*
import java.io.File

class AddBook : AppCompatActivity() {

    private lateinit var pathBook:TextView

    @javax.inject.Inject
    lateinit var factory:AddBookViewModelFactory

    lateinit var viewModel: AddBookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        (applicationContext as App).appComponent.injectAddBook(this)

        val nameBook = findViewById<EditText>(R.id.name_book_add)
        val btnFind = findViewById<Button>(R.id.btn_find_pdf)
        val btnSendBook = findViewById<Button>(R.id.btn_add_book)
        val progressbar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        val textProgress = findViewById<TextView>(R.id.textProgress)
        pathBook = findViewById(R.id.textViewNameFile)
        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
        val viewTitle = findViewById<TextView>(R.id.viewTitle)
        viewTitle.text = resources.getString(R.string.text_toolbar_AddBook)

        viewModel = ViewModelProvider(this,factory)
            .get(AddBookViewModel::class.java)

        viewModel.errorMessage.observe(this){
            UseAlert.errorMessage(it,this)
        }

        viewModel.visibleProgressBar.observe(this){
            if (it){
                nameBook.visibility = View.GONE
                pathBook.visibility = View.GONE
                btnFind.visibility = View.GONE
                btnSendBook.visibility = View.GONE
                textProgress.visibility = View.VISIBLE
                progressbar.apply {
                    visibility = View.VISIBLE
                    progressBarWidth = 14f
                    backgroundProgressBarWidth = 2f
                    backgroundProgressBarColor = Color.parseColor("#969696")
                    progressBarColorStart = Color.parseColor("#6767bb")
                    progressBarColorEnd = Color.parseColor("#4c7daf")
                    roundBorder = true
                    indeterminateMode = true
                }
                window?.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
        }

        viewModel.send.observe(this){
            if (it){
                textProgress.text = resources.getString(R.string.file_download_done)
            }else{
                textProgress.text = resources.getString(R.string.file_download_error)
            }
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main){
                    delay(2000)
                    window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    finish()
                }
            }
        }

        btnFind.setOnClickListener {
            viewModel.pathFile = null
            val intent = Intent("com.sec.android.app.myfiles.PICK_DATA")
            intent.putExtra("CONTENT_TYPE", "application/pdf")
            getResult.launch(intent)
        }

        btnSendBook.setOnClickListener {
            if (TextUtils.isEmpty(nameBook.text.toString().trim())){
                nameBook.apply {
                    setText("")
                    hint = resources.getString(R.string.nameBook_input_hint)
                }
            }
            if (viewModel.pathFile==null){
                viewModel.errorMessage.value = resources.getString(R.string.file_error_select)
            }
            if (!TextUtils.isEmpty(nameBook.text.toString().trim())&&viewModel.pathFile!=null){
                viewModel.sendFile(nameBook.text.toString(), intent.extras?.getInt("num_class")!!)
            }
        }
    }

    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            viewModel.pathFile = getRealPathFromURI(it.data?.data!!)
            viewModel.file = File(viewModel.pathFile)
            pathBook.text = resources.getString(R.string.file_selected) + " ${viewModel.file.nameWithoutExtension}"
        }
    }

    private fun getRealPathFromURI(uri: Uri): String? {
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        val idx: Int = cursor!!.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        return cursor.getString(idx)
    }
}