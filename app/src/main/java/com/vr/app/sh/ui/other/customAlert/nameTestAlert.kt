package com.vr.app.sh.ui.other.customAlert

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.vr.app.sh.R

class nameTestAlert(private val intent: Intent): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = LayoutInflater.from(context)
        val window: View = inflater.inflate(R.layout.alert_name_test,null)
        val inputNameTest = window.findViewById<TextInputEditText>(R.id.textNameTest)

        val btnNext = window.findViewById<MaterialButton>(R.id.btn_done)
        btnNext.setOnClickListener {
            if(TextUtils.isEmpty(inputNameTest.text.toString().trim())){
                error(requireContext().resources.getString(R.string.window_add_test_error))
            }else{
                intent.putExtra("name_test",inputNameTest.text.toString())
                startActivity(intent)
                dialog?.dismiss()
            }
        }

        val dialog = MaterialAlertDialogBuilder(requireActivity()).apply {
            setView(window)
        }.create()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }
}