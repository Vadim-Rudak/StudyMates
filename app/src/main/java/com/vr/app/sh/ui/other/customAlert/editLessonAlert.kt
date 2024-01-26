package com.vr.app.sh.ui.other.customAlert

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vr.app.sh.R

class editLessonAlert(): DialogFragment() {

    private lateinit var dialog: AlertDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = LayoutInflater.from(context)
        val window: View = inflater.inflate(R.layout.alert_edit_lesson,null)

        val btnEdit = window.findViewById<MaterialButton>(R.id.btn_edit_lesson)
        btnEdit.setOnClickListener {

        }
        val btnDelete = window.findViewById<MaterialButton>(R.id.btn_delete_lesson)
        btnDelete.setOnClickListener {

        }

        dialog = MaterialAlertDialogBuilder(requireActivity()).apply {
            setView(window)
        }.create()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }
}