package com.vr.app.sh.ui.other.customAlert

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vr.app.sh.R

class infoAlert(private val textInfo:String): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = LayoutInflater.from(context)
        val window: View = inflater.inflate(R.layout.alert_info,null)

        val viewTitel = window.findViewById<TextView>(R.id.al_info_view_titel)
        viewTitel.text = context?.getText(R.string.alrInfoTitel)
        val viewInfo = window.findViewById<TextView>(R.id.al_info_view_info)
        viewInfo.text = textInfo

        val btnOk = window.findViewById<MaterialButton>(R.id.btn_done)
        btnOk.setOnClickListener {
            dialog?.dismiss()
        }

        val dialog = MaterialAlertDialogBuilder(requireActivity()).apply {
            setView(window)
        }.create()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }
}