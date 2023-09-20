package com.vr.app.sh.ui.other

import android.content.Context
import android.content.DialogInterface
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vr.app.sh.R
import com.vr.app.sh.ui.door.view.Authoriz
import com.vr.app.sh.ui.other.customAlert.infoAlert
import com.vr.app.sh.ui.other.customAlert.loadingAlert

class UseAlert{
    companion object {
        fun errorMessage(textMessage:String,context: Context){
            val alertDialog = MaterialAlertDialogBuilder(context)
            alertDialog.setTitle(context.getString(R.string.wrongTitel))
            alertDialog.setMessage(textMessage)
            alertDialog.setPositiveButton(context.getString(R.string.alrBtnOk), DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            alertDialog.show()
        }

        fun infoMessage(fragmentManager: FragmentManager,textMessage: String){
            val alertInfo = infoAlert(textMessage)
            alertInfo.show(fragmentManager,"AlertInfo")
        }

        fun loading(textMessage: String):loadingAlert{
            return loadingAlert(textMessage)
        }

    }
}