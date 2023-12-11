package com.vr.app.sh.ui.other.customAlert

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vr.app.sh.R
import com.vr.app.sh.domain.model.questions.TypeQuestion

class addQuestionAlert(private val typeQ:MutableLiveData<TypeQuestion>): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = LayoutInflater.from(context)
        val window: View = inflater.inflate(R.layout.alert_add_question,null)

        val btnAddAnswers = window.findViewById<MaterialButton>(R.id.btn_add_for_answers)
        btnAddAnswers.setOnClickListener {
            click(TypeQuestion.Answers)
        }
        val btnAddImg = window.findViewById<MaterialButton>(R.id.btn_with_img)
        btnAddImg.setOnClickListener {
            click(TypeQuestion.Img)
        }
        val btnToWrite = window.findViewById<MaterialButton>(R.id.btn_to_write)
        btnToWrite.setOnClickListener {
            click(TypeQuestion.Write)
        }

        val dialog = MaterialAlertDialogBuilder(requireActivity()).apply {
            setView(window)
        }.create()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    private fun click(typeQuestion: TypeQuestion){
        typeQ.postValue(typeQuestion)
        dialog?.dismiss()
    }
}