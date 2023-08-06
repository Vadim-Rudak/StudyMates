package com.vr.app.sh.ui.other

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class UseAlert{
    companion object {
        fun errorMessage(textMessage:String,context: Context){
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Ошибка")
            alertDialog.setMessage(textMessage)
            alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            alertDialog.show()
        }
    }
}