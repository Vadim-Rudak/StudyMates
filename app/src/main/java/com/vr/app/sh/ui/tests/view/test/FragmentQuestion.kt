package com.vr.app.sh.ui.tests.view.test

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.vr.app.sh.R
import com.vr.app.sh.domain.model.Question

class FragmentQuestion(var num_question:Int, var listQuestions: List<Question>, var info_questions:Array<Int>, var tabLayout: TabLayout) : Fragment(), View.OnClickListener {

    private lateinit var textQuestion:TextView
    private lateinit var btnAnswer1: MaterialButton
    private lateinit var btnAnswer2:MaterialButton
    private lateinit var btnAnswer3:MaterialButton
    private lateinit var btnAnswer4:MaterialButton
    private lateinit var btnConfirm:MaterialButton
    private var correctOtv:String = ""
    private var num_click_btn:Int = 0
    private lateinit var Ok_image: ImageView
    private lateinit var Ok_text:TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        textQuestion = view.findViewById(R.id.TextQuestion)
        btnAnswer1 = view.findViewById(R.id.BtnOtv1)
        btnAnswer1.setOnClickListener(this)
        btnAnswer2 = view.findViewById(R.id.BtnOtv2)
        btnAnswer2.setOnClickListener(this)
        btnAnswer3 = view.findViewById(R.id.BtnOtv3)
        btnAnswer3.setOnClickListener(this)
        btnAnswer4 = view.findViewById(R.id.BtnOtv4)
        btnAnswer4.setOnClickListener(this)
        btnConfirm = view.findViewById(R.id.btnOk)
        btnConfirm.setOnClickListener(this)
        Ok_image = view.findViewById(R.id.image_ok_question)
        Ok_text = view.findViewById(R.id.Ok_text)
        setInfoInQuestion()
        ViewUseThisQuestion()
        return view
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (!menuVisible && isResumed){
            ViewUseThisQuestion()
        }
    }

    private fun ViewUseThisQuestion(){
        if (tabLayout.getTabAt(num_question)?.text.toString().equals("✓")||
            tabLayout.getTabAt(num_question)?.text.toString().equals("X")){

            textQuestion.visibility = View.GONE
            btnAnswer1.visibility = View.GONE
            btnAnswer2.visibility = View.GONE
            btnAnswer3.visibility = View.GONE
            btnAnswer4.visibility = View.GONE
            btnConfirm.visibility = View.GONE
            Ok_image.visibility = View.VISIBLE
            Ok_text.visibility = View.VISIBLE
        }else{
            textQuestion.visibility = View.VISIBLE
            btnAnswer1.visibility = View.VISIBLE
            btnAnswer2.visibility = View.VISIBLE
            btnAnswer3.visibility = View.VISIBLE
            btnAnswer4.visibility = View.VISIBLE
            btnConfirm.visibility = View.VISIBLE
            Ok_image.visibility = View.GONE
            Ok_text.visibility = View.GONE
        }
    }

    private fun setInfoInQuestion(){
        textQuestion.text = listQuestions[num_question].question
        btnAnswer1.text = listQuestions[num_question].answer1
        btnAnswer2.text = listQuestions[num_question].answer2
        btnAnswer3.text = listQuestions[num_question].answer3
        btnAnswer4.text = listQuestions[num_question].answer4
        correctOtv = listQuestions[num_question].correctAnswer.toString()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.BtnOtv1 ->{
                cleanSelectedBtn()
                setColorsInButton(btnAnswer1,AnswerStatus.SELECT)
                num_click_btn = 1
            }
            R.id.BtnOtv2 ->{
                cleanSelectedBtn()
                setColorsInButton(btnAnswer2,AnswerStatus.SELECT)
                num_click_btn = 2
            }
            R.id.BtnOtv3 ->{
                cleanSelectedBtn()
                setColorsInButton(btnAnswer3,AnswerStatus.SELECT)
                num_click_btn = 3
            }
            R.id.BtnOtv4 ->{
                cleanSelectedBtn()
                setColorsInButton(btnAnswer4,AnswerStatus.SELECT)
                num_click_btn = 4
            }
            R.id.btnOk ->{
                if (num_click_btn != 0){
                    checkSelectedAnswer(
                        selectedBtn = when(num_click_btn){
                            1 -> btnAnswer1
                            2 -> btnAnswer2
                            3 -> btnAnswer3
                            else -> btnAnswer4
                        }
                    )

                    btnConfirm.setBackgroundColor(Color.parseColor("#9d9d9d"))
                    btnConfirm.setTextColor(Color.parseColor("#d2d2d2"))
                    btnConfirm.isClickable = false
                    btnAnswer1.isClickable = false
                    btnAnswer2.isClickable = false
                    btnAnswer3.isClickable = false
                    btnAnswer4.isClickable = false
                }else{
                    Log.d("FFF","Выберите ответ")
                }
            }
        }
    }

    private fun cleanSelectedBtn(){
        setColorsInButton(
            button = when(num_click_btn){
                1 -> btnAnswer1
                2 -> btnAnswer2
                3 -> btnAnswer3
                else -> btnAnswer4
            },
            status = AnswerStatus.NOT_SELECT
        )
    }

    private fun setColorsInButton(button:MaterialButton, status:AnswerStatus){
        button.apply {
            setBackgroundColor(ContextCompat.getColor(requireActivity(),
                when(status){
                    AnswerStatus.SELECT -> R.color.select_answer
                    AnswerStatus.CORRECT -> R.color.correct_answer
                    AnswerStatus.ERROR -> R.color.error_answer
                    else -> R.color.not_select_answer
                }
            ))
            setTextColor(ContextCompat.getColor(requireActivity(),
                if (status == AnswerStatus.NOT_SELECT){
                    R.color.black
                }else{
                    R.color.white
                })
            )
        }
    }

    private fun checkSelectedAnswer(selectedBtn:MaterialButton){
        if(selectedBtn.text.equals(correctOtv)){
            setColorsInButton(selectedBtn,AnswerStatus.CORRECT)
            info_questions[num_question] = 1
            tabLayout.getTabAt(num_question)?.text = "✓"
        }else{
            setColorsInButton(selectedBtn,AnswerStatus.ERROR)
            info_questions[num_question] = 2
            tabLayout.getTabAt(num_question)?.text = "X"
        }
    }
}