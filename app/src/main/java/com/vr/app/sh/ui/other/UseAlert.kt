package com.vr.app.sh.ui.other

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.vr.app.sh.R

class UseAlert{
    companion object {
        fun errorMessage(textMessage:String,context: Context){
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle(context.getString(R.string.wrongTitel))
            alertDialog.setMessage(textMessage)
            alertDialog.setPositiveButton(context.getString(R.string.alrBtnOk), DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            alertDialog.show()
        }
    }
}