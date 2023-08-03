package com.vr.app.sh.ui.books.viewmodel

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.R
import com.vr.app.sh.data.model.Book
import com.vr.app.sh.data.repository.RoomDB
import com.vr.app.sh.domain.UseCase.GetBookFile
import com.vr.app.sh.domain.UseCase.GetListBookInClass
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.ui.books.adapter.RecyclerViewAdapter
import com.vr.app.sh.ui.books.view.AddBook
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*

class SubjectsViewModel(context: Context,val getBookFile: GetBookFile, val getListBookInClass: GetListBookInClass,val internetConnection: InternetConnection,val numClass:Int): ViewModel() {

    val download = MutableLiveData<Boolean>()
    var saveFileInMemory:Boolean = false
    var progress = MutableLiveData<Long>()
    val adapter = RecyclerViewAdapter()
    val errorMessage = MutableLiveData<String>()
    var listBooks: LiveData<List<Book>> = getListBookInClass.execute(numClass)
    //var job: Job? = null


    fun editMenu(context: Context){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Выберите функцию")

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

        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
        alertDialog.show()
    }

    fun errorMessage(textMessage:String,context:Context){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Ошибка")
        alertDialog.setMessage(textMessage)
        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
        alertDialog.show()
    }

    fun saveFileDialog(path: String,id_book: Int,context: Context){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Учебник не скачан, загрузить его?")
        alertDialog.setPositiveButton("Да", DialogInterface.OnClickListener { dialogInterface, i ->
            downloadFile(path,id_book)
        })
        alertDialog.setNegativeButton("Отмена",DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
        alertDialog.show()
    }

    fun downloadFile(path: String,id_book:Int) {
        if(internetConnection.UseInternet()){
            download.postValue(true)
            Log.d("downloadFile", "start")
            val call = getBookFile.execute(id_book)
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    try {
                        CoroutineScope(Dispatchers.IO).launch {
                            if (response!!.isSuccessful){
                                val writtenToDisk = writeResponseBodyToDisk(response.body(), path)
                                Log.d("download", "file download was a success? $writtenToDisk")
                                Log.d("downloadFile", "sucess")
                                saveFileInMemory = writtenToDisk
                                if (!writtenToDisk){
                                    val file = File(path)
                                    if (file.exists()){
                                        file.delete()
                                    }
                                    withContext(Dispatchers.Main){
                                        errorMessage.value = "Ошибка скачивания файла, попробуйте ещё раз"
                                        download.value = false
                                    }
                                }
                            }else{
                                errorMessage.value = response.message()
                            }
                        }
                        Log.d("onResponse", "Response came from server")
                    } catch (e: IOException) {
                        Log.d("onResponse", "There is an error")
                        e.printStackTrace()
                    }
                }
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    Log.d("onFailure", t.toString())
                }
            })
        }else{
            errorMessage.value = "Нет подключения к интернету"
        }
    }
    private suspend fun writeResponseBodyToDisk(body: ResponseBody?, path_book: String): Boolean {
        try {

            var futureStudioIconFile = File(path_book)
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null

            try {
                val fileReader = ByteArray(4096)

                val fileSize = body?.contentLength()
                var fileSizeDownloaded: Long = 0

                inputStream = body?.byteStream()
                outputStream = FileOutputStream(futureStudioIconFile)

                while (true) {
                    val read = inputStream!!.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream!!.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                    progress.postValue(fileSizeDownloaded*100/ fileSize!!)
                }
                outputStream!!.flush()
                return true
            } catch (e: IOException) {
                return false
            } finally {
                if (inputStream != null) {
                    inputStream!!.close()
                }
                if (outputStream != null) {
                    outputStream!!.close()
                }
            }
        } catch (e: IOException) {
            return false
        }
    }

//    override fun onCleared() {
//        super.onCleared()
//        job?.cancel()
//    }
}