package com.vr.app.sh.ui.tests.view.test

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.vr.app.sh.R
import com.vr.app.sh.domain.model.Question
import com.vr.app.sh.ui.other.UseAlert.Companion.infoMessage

class FragmentQuestion(
    private var numQuestion:Int,
    private var listQuestions: List<Question>,
    private var infoQuestions:Array<Int>,
    private var tabLayout: TabLayout
) : Fragment(), View.OnClickListener {

    private lateinit var textQuestion:TextView
    private lateinit var btnAnswer1: MaterialButton
    private lateinit var btnAnswer2:MaterialButton
    private lateinit var btnAnswer3:MaterialButton
    private lateinit var btnAnswer4:MaterialButton
    private lateinit var btnConfirm:MaterialButton
    private var correctOtv:String = ""
    private var numClickBtn:Int = 0
    private lateinit var animAnswered:LottieAnimationView
    private lateinit var textAnswered:TextView

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
        animAnswered = view.findViewById(R.id.animAnswered)
        textAnswered = view.findViewById(R.id.Ok_text)
        setInfoInQuestion()
        viewUseThisQuestion()
        return view
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (!menuVisible && isResumed){
            viewUseThisQuestion()
        }
    }

    private fun viewUseThisQuestion(){
        if (tabLayout.getTabAt(numQuestion)?.text.toString().equals("✓")||
            tabLayout.getTabAt(numQuestion)?.text.toString().equals("X")){

            textQuestion.visibility = View.GONE
            btnAnswer1.visibility = View.GONE
            btnAnswer2.visibility = View.GONE
            btnAnswer3.visibility = View.GONE
            btnAnswer4.visibility = View.GONE
            btnConfirm.visibility = View.GONE
            animAnswered.visibility = View.VISIBLE
            val animationFadeIn = AnimationUtils.loadAnimation(requireActivity(), R.anim.anim_fade_in)
            animAnswered.apply {
                startAnimation(animationFadeIn)
                setAnimation("question_completed.json")
            }.playAnimation()
            textAnswered.visibility = View.VISIBLE
        }else{
            textQuestion.visibility = View.VISIBLE
            btnAnswer1.visibility = View.VISIBLE
            btnAnswer2.visibility = View.VISIBLE
            btnAnswer3.visibility = View.VISIBLE
            btnAnswer4.visibility = View.VISIBLE
            btnConfirm.visibility = View.VISIBLE
            animAnswered.visibility = View.GONE
            textAnswered.visibility = View.GONE
        }
    }

    private fun setInfoInQuestion(){
        textQuestion.text = listQuestions[numQuestion].question
        btnAnswer1.text = listQuestions[numQuestion].answer1
        btnAnswer2.text = listQuestions[numQuestion].answer2
        btnAnswer3.text = listQuestions[numQuestion].answer3
        btnAnswer4.text = listQuestions[numQuestion].answer4
        correctOtv = listQuestions[numQuestion].correctAnswer.toString()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.BtnOtv1 ->{
                cleanSelectedBtn()
                setColorsInButton(btnAnswer1,AnswerStatus.SELECT)
                numClickBtn = 1
            }
            R.id.BtnOtv2 ->{
                cleanSelectedBtn()
                setColorsInButton(btnAnswer2,AnswerStatus.SELECT)
                numClickBtn = 2
            }
            R.id.BtnOtv3 ->{
                cleanSelectedBtn()
                setColorsInButton(btnAnswer3,AnswerStatus.SELECT)
                numClickBtn = 3
            }
            R.id.BtnOtv4 ->{
                cleanSelectedBtn()
                setColorsInButton(btnAnswer4,AnswerStatus.SELECT)
                numClickBtn = 4
            }
            R.id.btnOk ->{
                if (numClickBtn != 0){
                    checkSelectedAnswer(
                        selectedBtn = when(numClickBtn){
                            1 -> btnAnswer1
                            2 -> btnAnswer2
                            3 -> btnAnswer3
                            else -> btnAnswer4
                        }
                    )

                    btnConfirm.apply {
                        setBackgroundColor(Color.parseColor("#9d9d9d"))
                        setTextColor(Color.parseColor("#d2d2d2"))
                        isClickable = false
                    }
                    btnAnswer1.isClickable = false
                    btnAnswer2.isClickable = false
                    btnAnswer3.isClickable = false
                    btnAnswer4.isClickable = false
                }else{
                    infoMessage(childFragmentManager,resources.getString(R.string.question_error_select))
                }
            }
        }
    }

    private fun cleanSelectedBtn(){
        setColorsInButton(
            button = when(numClickBtn){
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
            infoQuestions[numQuestion] = 1
            tabLayout.getTabAt(numQuestion)?.text = "✓"
        }else{
            setColorsInButton(selectedBtn,AnswerStatus.ERROR)
            infoQuestions[numQuestion] = 2
            tabLayout.getTabAt(numQuestion)?.text = "X"
        }
    }
}