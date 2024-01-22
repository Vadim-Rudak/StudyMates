package com.vr.app.sh.ui.other.customAlert

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vr.app.sh.R

class verificationAlert(private val nameAnim:String, private val textTitle:String, private val textInfo:String): DialogFragment() {

    private lateinit var dialog: AlertDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = LayoutInflater.from(context)
        val window: View = inflater.inflate(R.layout.alert_loading,null)

        val viewAnimation = window.findViewById<LottieAnimationView>(R.id.al_loading_anim)
        viewAnimation.apply {
            repeatCount = LottieDrawable.INFINITE
            repeatMode = LottieDrawable.RESTART
            setAnimation(nameAnim)
        }.playAnimation()

        val viewTitle = window.findViewById<TextView>(R.id.al_loading_view_titel)
        viewTitle.text = textTitle
        val viewInfo = window.findViewById<TextView>(R.id.al_loading_view_info)
        viewInfo.text = textInfo

        dialog = MaterialAlertDialogBuilder(requireActivity()).apply {
            setView(window)
        }.create()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    fun isDone(){
        dialog.dismiss()
    }
}