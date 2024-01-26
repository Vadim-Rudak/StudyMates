package com.vr.app.sh.ui.other

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.fragment.app.FragmentManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vr.app.sh.R
import com.vr.app.sh.ui.other.customAlert.editLessonAlert
import com.vr.app.sh.ui.other.customAlert.infoAlert
import com.vr.app.sh.ui.other.customAlert.loadingAlert
import com.vr.app.sh.ui.other.customAlert.nameTestAlert
import com.vr.app.sh.ui.other.customAlert.permissionAlert
import com.vr.app.sh.ui.other.customAlert.verificationAlert

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

        fun loading(textTitle:String, textMessage: String):loadingAlert{
            return loadingAlert(textTitle,textMessage)
        }

        fun verificationMsg(nameAnim: String, textTitle:String, textMessage: String):verificationAlert{
            return verificationAlert(nameAnim,textTitle,textMessage)
        }


        fun permissionMsg(fragmentManager: FragmentManager, id:Int, title:String, info:String, nameAnim:String){
            permissionAlert(id,title,info,nameAnim).show(fragmentManager,"permissionAlert")
        }

        fun writeNameTest(intent: Intent):nameTestAlert{
            return nameTestAlert(intent)
        }

        fun editLesson():editLessonAlert{
            return editLessonAlert()
        }
    }
}