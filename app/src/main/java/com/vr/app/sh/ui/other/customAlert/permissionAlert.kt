package com.vr.app.sh.ui.other.customAlert

import android.Manifest
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vr.app.sh.R

class permissionAlert(private val idPermission:Int,private val textTitel:String, private val textInfo:String,private val nameAnim:String): DialogFragment() {

    private lateinit var dialog: AlertDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = LayoutInflater.from(context)
        val window: View = inflater.inflate(R.layout.alert_permission,null)

        val viewAnimation = window.findViewById<LottieAnimationView>(R.id.al_loading_anim)
        viewAnimation.apply {
            repeatCount = LottieDrawable.INFINITE
            repeatMode = LottieDrawable.RESTART
            setAnimation(nameAnim)
        }.playAnimation()

        val viewTitle = window.findViewById<TextView>(R.id.al_loading_view_titel)
        viewTitle.text = textTitel
        val viewInfo = window.findViewById<TextView>(R.id.al_loading_view_info)
        viewInfo.text = textInfo

        val btnOk = window.findViewById<MaterialButton>(R.id.btn_permission_done)
        btnOk.setOnClickListener {
            when(idPermission){
                0->{
                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
                    dialog.dismiss()
                }
                1->{
                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA), 1)
                    dialog.dismiss()
                }
            }
        }

        val btnCansel = window.findViewById<Button>(R.id.btn_permission_cansel)
        btnCansel.setOnClickListener {
            dialog.dismiss()
        }

        dialog = MaterialAlertDialogBuilder(requireActivity()).apply {
            setView(window)
        }.create()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

}