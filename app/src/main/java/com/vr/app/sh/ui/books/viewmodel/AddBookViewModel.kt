package com.vr.app.sh.ui.books.viewmodel

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.app.sh.R
import com.vr.app.sh.domain.UseCase.GetAllBookListInternet
import com.vr.app.sh.domain.UseCase.SaveBookListInBD
import com.vr.app.sh.domain.UseCase.SendBook
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class AddBookViewModel(
    private val resources: Resources,
    val getAllBookListInternet: GetAllBookListInternet,
    val saveBookListInBD: SaveBookListInBD,
    private val internetConnection:Boolean,
    private val sendBook: SendBook
): ViewModel() {

    var path_file: String? = null
    lateinit var file: File
    val vizibileProgressBar = MutableLiveData<Boolean>()
    val send = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun sendFile(nameBook:String,numClass:Int){
        if(internetConnection){
            vizibileProgressBar.value = true
            CoroutineScope(Dispatchers.IO).launch {
                sendBook.execute(numClass,nameBook,file)
                getAllBookListInternet.execute().also {
                    if (it.success){
                        it.list?.let { it1 -> saveBookListInBD.execute(it1) }
                        withContext(Dispatchers.Main){
                            vizibileProgressBar.value = false
                            send.value = true
                        }
                    }
                }
            }
        }else{
            vizibileProgressBar.value = false
            errorMessage.value = resources.getString(R.string.alrNotInternetConnection)
        }
    }
}