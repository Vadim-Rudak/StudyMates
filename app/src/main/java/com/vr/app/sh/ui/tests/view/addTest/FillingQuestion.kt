package com.vr.app.sh.ui.tests.view.addTest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.doAfterTextChanged
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.vr.app.sh.R
import com.vr.app.sh.data.repository.RegistrationInfo
import com.vr.app.sh.domain.model.questions.Question
import com.vr.app.sh.domain.model.questions.QuestionWithAnswers
import com.vr.app.sh.domain.model.questions.TypeQuestion
import com.vr.app.sh.ui.other.photoPicker.BottomSheetPickPhoto

class FillingQuestion(private val question: Question) : Fragment() {

    private var bottomSheetPickPhoto: BottomSheetPickPhoto? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = when(question.type){
            TypeQuestion.Answers->inflater.inflate(R.layout.question_write_answes, container, false)
            TypeQuestion.Img->inflater.inflate(R.layout.question_write_img, container, false)
            TypeQuestion.Write->inflater.inflate(R.layout.question_write_in_fild, container, false)
            else->inflater.inflate(R.layout.fragment_question_type, container, false)
        }

        when(question.type){
            TypeQuestion.Answers->{
                val questionA = question as QuestionWithAnswers
                val questionInp = view.findViewById<EditText>(R.id.InputQuestion)
                questionInp.doAfterTextChanged {
                     questionA.question = it.toString()
                }
                val answer1Inp = view.findViewById<EditText>(R.id.InputOtv1)
                answer1Inp.doAfterTextChanged {
                    questionA.otv1 = it.toString()
                }
                val answer2Inp = view.findViewById<EditText>(R.id.InputOtv2)
                answer2Inp.doAfterTextChanged {
                    questionA.otv2 = it.toString()
                }
                val answer3Inp = view.findViewById<EditText>(R.id.InputOtv3)
                answer3Inp.doAfterTextChanged {
                    questionA.otv3 = it.toString()
                }
                val answer4Inp = view.findViewById<EditText>(R.id.InputOtv4)
                answer4Inp.doAfterTextChanged {
                    questionA.otv4 = it.toString()
                }
                val correctAnswerInp = view.findViewById<EditText>(R.id.InputCorrectOtv)
                correctAnswerInp.doAfterTextChanged {
                    questionA.correctAnswer = it.toString()
                }
            }
            TypeQuestion.Img->{
                val placeReg = activity?.findViewById<CoordinatorLayout>(R.id.place_reg)
                val viewBottomSheet = activity?.findViewById<LinearLayout>(R.id.sheet_pick_photo)
                bottomSheetPickPhoto = BottomSheetPickPhoto(requireContext(), viewBottomSheet!!, placeReg!!.height)

                val btnSelectImg = view.findViewById<ShapeableImageView>(R.id.select_img)
                btnSelectImg.setOnClickListener {
                    bottomSheetPickPhoto!!.see()
                }

                bottomSheetPickPhoto!!.onePhoto.observe(viewLifecycleOwner){
                    Glide.with(requireContext()).load(it).into(btnSelectImg)
                }
            }
            TypeQuestion.Write->{}
            else -> {}
        }

        return view
    }

}