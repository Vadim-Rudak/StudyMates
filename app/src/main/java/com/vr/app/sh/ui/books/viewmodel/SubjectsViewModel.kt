package com.vr.app.sh.ui.books.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.R
import com.vr.app.sh.domain.UseCase.GetBookFile
import com.vr.app.sh.domain.UseCase.GetListBookInClass
import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.ui.books.adapter.RecyclerViewAdapter
import com.vr.app.sh.ui.books.view.AddBook
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubjectsViewModel(private val resources: Resources,val getBookFile: GetBookFile, val getListBookInClass: GetListBookInClass,val numClass:Int,private val internetConnect:Boolean): ViewModel() {

    val download = MutableLiveData<Boolean>()
    var saveFileInMemory:Boolean? = false
    var progress = MutableLiveData<Long>()
    val adapter = RecyclerViewAdapter()
    val errorMessage = MutableLiveData<String>()
    var listBooks = MutableLiveData<List<Book>>()
    var job: Job? = null

    init {
        fetchListBooks()
    }

    private fun fetchListBooks () {
        job = CoroutineScope(Dispatchers.IO).launch {
            getListBookInClass.execute(numClass).collectIndexed { index, value ->
                listBooks.postValue(value)
            }
        }
    }


    fun editMenu(context: Context){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(resources.getString(R.string.editBookTitel))

        val inflater = LayoutInflater.from(context)
        val window:View = inflater.inflate(R.layout.edit_book_window,null)
        alertDialog.setView(window)

        val btn_edit_book_info = window.findViewById<Button>(R.id.btn_edit_info)
        btn_edit_book_info.setOnClickListener {

        }

        val btn_up_file = window.findViewById<Button>(R.id.btn_up_file)
        btn_up_file.setOnClickListener {

        }

        val btnDeleteBook = window.findViewById<Button>(R.id.btnDelBook)
        btnDeleteBook.setOnClickListener {
            val intent = Intent(context,AddBook::class.java)
            context.startActivity(intent)
        }

        alertDialog.setPositiveButton(resources.getString(R.string.editBookBtnOk), DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
        alertDialog.show()
    }

    fun saveFileDialog(path: String,id_book: Int,context: Context){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(resources.getString(R.string.titelDownloadBook))
        alertDialog.setPositiveButton(resources.getString(R.string.btnDownloadBookOk), DialogInterface.OnClickListener { dialogInterface, i ->
            downloadFile(path,id_book)
        })
        alertDialog.setNegativeButton(resources.getString(R.string.btnDownloadBookCansel),DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
        alertDialog.show()
    }

    fun downloadFile(path: String,id_book:Int) {
        if(internetConnect){
            download.postValue(true)
            Log.d("downloadFile", "start")
            job = CoroutineScope(Dispatchers.IO).launch {
                getBookFile.execute(id_book, path).collectIndexed { index, value ->
                    saveFileInMemory = value.success
                    progress.postValue(value.progress)
                    if (value.success == false){
                        withContext(Dispatchers.Main){
                            errorMessage.value = resources.getString(R.string.alrErrorDownloadBook)
                            download.value = false
                        }
                    }
                }
            }
        }else{
            errorMessage.value = resources.getString(R.string.alrNotInternetConnection)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}