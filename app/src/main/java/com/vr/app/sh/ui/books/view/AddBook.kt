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
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.AddBookViewModelFactory
import com.vr.app.sh.ui.books.viewmodel.AddBookViewModel
import kotlinx.coroutines.*
import java.io.File

class AddBook : AppCompatActivity() {

    lateinit var pathBook:TextView

    @javax.inject.Inject
    lateinit var factory:AddBookViewModelFactory

    lateinit var viewModel: AddBookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        (applicationContext as App).appComponent.injectAddBook(this)

        val nameBook = findViewById<EditText>(R.id.name_book_add)
        val btn_find = findViewById<Button>(R.id.btn_find_pdf)
        val btn_sendBook = findViewById<Button>(R.id.btn_add_book)
        val progressbar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        val textProgress = findViewById<TextView>(R.id.textProgress)
        pathBook = findViewById(R.id.textViewNameFile)

        viewModel = ViewModelProvider(this,factory)
            .get(AddBookViewModel::class.java)

        viewModel.errorMessage.observe(this){
            viewModel.errorMessage(it,this)
        }

        viewModel.vizibileProgressBar.observe(this){
            if (it){
                nameBook.visibility = View.GONE
                pathBook.visibility = View.GONE
                btn_find.visibility = View.GONE
                btn_sendBook.visibility = View.GONE
                progressbar.visibility = View.VISIBLE
                textProgress.visibility = View.VISIBLE
                progressbar.apply {
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
                textProgress.setText("Файл успешно загружен")
            }else{
                textProgress.setText("Ошибка загрузки файла")
            }
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main){
                    delay(2000)
                    window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    finish()
                }
            }
        }

        btn_find.setOnClickListener {
            viewModel.path_file = null
            val intent = Intent("com.sec.android.app.myfiles.PICK_DATA")
            intent.putExtra("CONTENT_TYPE", "application/pdf")
            getResult.launch(intent)
        }

        btn_sendBook.setOnClickListener {
            if (TextUtils.isEmpty(nameBook.text.toString().trim())){
                nameBook.setText("")
                nameBook.hint = "Введите название книги"
            }
            if (viewModel.path_file==null){
                viewModel.errorMessage.value = "Не выбран файл для отправки"
            }
            if (!TextUtils.isEmpty(nameBook.text.toString().trim())&&viewModel.path_file!=null){
                viewModel.sendFile(nameBook.text.toString(), intent.extras?.getInt("num_class")!!)
            }
        }
    }

    val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            viewModel.path_file = getRealPathFromURI(it.data?.data!!)
            viewModel.file = File(viewModel.path_file)
            pathBook.text = "Выбранный файл: ${ viewModel.file.name.substring(0,viewModel.file.name.length-4) }"
        }
    }

    fun getRealPathFromURI(uri: Uri): String? {
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        val idx: Int = cursor!!.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        return cursor.getString(idx)
    }
}