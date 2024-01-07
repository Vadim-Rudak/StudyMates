package com.vr.app.sh.ui.tests.view.test

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.vr.app.sh.R
import com.vr.app.sh.domain.model.Question

class FragmentQuestion(var num_question:Int, var listQuestions: List<Question>, var info_questions:Array<Int>, var tabLayout: TabLayout) : Fragment(), View.OnClickListener {

    lateinit var textQuestion:TextView
    lateinit var btnOtv1:Button
    lateinit var btnOtv2:Button
    lateinit var btnOtv3:Button
    lateinit var btnOtv4:Button
    lateinit var btnConfirm:Button
    var correctOtv:String = ""
    var num_click_btn:Int = 0
    lateinit var Ok_image: ImageView
    lateinit var Ok_text:TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_question, container, false)
        textQuestion = v.findViewById(R.id.TextQuestion)
        btnOtv1 = v.findViewById(R.id.BtnOtv1)
        btnOtv1.setOnClickListener(this)
        btnOtv2 = v.findViewById(R.id.BtnOtv2)
        btnOtv2.setOnClickListener(this)
        btnOtv3 = v.findViewById(R.id.BtnOtv3)
        btnOtv3.setOnClickListener(this)
        btnOtv4 = v.findViewById(R.id.BtnOtv4)
        btnOtv4.setOnClickListener(this)
        btnConfirm = v.findViewById(R.id.btnOk)
        btnConfirm.setOnClickListener(this)
        Ok_image = v.findViewById(R.id.image_ok_question)
        Ok_text = v.findViewById(R.id.Ok_text)
        setInfoInQuestion()
        ViewUseThisQuestion()
        return v
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (!menuVisible && isResumed){
            ViewUseThisQuestion()
        }
    }

    fun ViewUseThisQuestion(){
        if (tabLayout.getTabAt(num_question)?.text.toString().equals("✓")||
            tabLayout.getTabAt(num_question)?.text.toString().equals("X")){

            textQuestion.visibility = View.GONE
            btnOtv1.visibility = View.GONE
            btnOtv2.visibility = View.GONE
            btnOtv3.visibility = View.GONE
            btnOtv4.visibility = View.GONE
            btnConfirm.visibility = View.GONE
            Ok_image.visibility = View.VISIBLE
            Ok_text.visibility = View.VISIBLE
        }else{
            textQuestion.visibility = View.VISIBLE
            btnOtv1.visibility = View.VISIBLE
            btnOtv2.visibility = View.VISIBLE
            btnOtv3.visibility = View.VISIBLE
            btnOtv4.visibility = View.VISIBLE
            btnConfirm.visibility = View.VISIBLE
            Ok_image.visibility = View.GONE
            Ok_text.visibility = View.GONE
        }
    }

    fun setInfoInQuestion(){
        textQuestion.text = listQuestions[num_question].question
        btnOtv1.text = listQuestions[num_question].answer1
        btnOtv2.text = listQuestions[num_question].answer2
        btnOtv3.text = listQuestions[num_question].answer3
        btnOtv4.text = listQuestions[num_question].answer4
        correctOtv = listQuestions[num_question].correctAnswer.toString()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.BtnOtv1 ->{
                when(num_click_btn){
                    2 ->{
                        btnOtv2.setBackgroundColor(Color.parseColor("#00FFFFFF"))
                        btnOtv2.setTextColor(Color.parseColor("#FF000000"))
                    }
                    3 ->{
                        btnOtv3.setBackgroundColor(Color.parseColor("#00FFFFFF"))
                        btnOtv3.setTextColor(Color.parseColor("#FF000000"))
                    }
                    4 ->{
                        btnOtv4.setBackgroundColor(Color.parseColor("#00FFFFFF"))
                        btnOtv4.setTextColor(Color.parseColor("#FF000000"))
                    }
                }
                btnOtv1.setBackgroundColor(Color.parseColor("#969696"))
                btnOtv1.setTextColor(Color.parseColor("#FFFFFFFF"))
                num_click_btn = 1
            }
            R.id.BtnOtv2 ->{
                when(num_click_btn){
                    1 ->{
                        btnOtv1.setBackgroundColor(Color.parseColor("#00FFFFFF"))
                        btnOtv1.setTextColor(Color.parseColor("#FF000000"))
                    }
                    3 ->{
                        btnOtv3.setBackgroundColor(Color.parseColor("#00FFFFFF"))
                        btnOtv3.setTextColor(Color.parseColor("#FF000000"))
                    }
                    4 ->{
                        btnOtv4.setBackgroundColor(Color.parseColor("#00FFFFFF"))
                        btnOtv4.setTextColor(Color.parseColor("#FF000000"))
                    }
                }
                btnOtv2.setBackgroundColor(Color.parseColor("#969696"))
                btnOtv2.setTextColor(Color.parseColor("#FFFFFFFF"))
                num_click_btn = 2
            }
            R.id.BtnOtv3 ->{
                when(num_click_btn){
                    1 ->{
                        btnOtv1.setBackgroundColor(Color.parseColor("#00FFFFFF"))
                        btnOtv1.setTextColor(Color.parseColor("#FF000000"))

                    }
                    2 ->{
                        btnOtv2.setBackgroundColor(Color.parseColor("#00FFFFFF"))
                        btnOtv2.setTextColor(Color.parseColor("#FF000000"))
                    }
                    4 ->{
                        btnOtv4.setBackgroundColor(Color.parseColor("#00FFFFFF"))
                        btnOtv4.setTextColor(Color.parseColor("#FF000000"))
                    }
                }
                btnOtv3.setBackgroundColor(Color.parseColor("#969696"))
                btnOtv3.setTextColor(Color.parseColor("#FFFFFFFF"))
                num_click_btn = 3
            }
            R.id.BtnOtv4 ->{
                when(num_click_btn){
                    1 ->{
                        btnOtv1.setBackgroundColor(Color.parseColor("#00FFFFFF"))
                        btnOtv1.setTextColor(Color.parseColor("#FF000000"))
                    }
                    2 ->{
                        btnOtv2.setBackgroundColor(Color.parseColor("#00FFFFFF"))
                        btnOtv2.setTextColor(Color.parseColor("#FF000000"))
                    }
                    3 ->{
                        btnOtv3.setBackgroundColor(Color.parseColor("#00FFFFFF"))
                        btnOtv3.setTextColor(Color.parseColor("#FF000000"))
                    }
                }
                btnOtv4.setBackgroundColor(Color.parseColor("#969696"))
                btnOtv4.setTextColor(Color.parseColor("#FFFFFFFF"))
                num_click_btn = 4
            }
            R.id.btnOk ->{
                if (num_click_btn != 0){
                    when(num_click_btn){
                        1 ->{
                            if(btnOtv1.text.equals(correctOtv)){
                                btnOtv1.setBackgroundColor(Color.parseColor("#4baf32"))
                                info_questions[num_question] = 1
                                tabLayout.getTabAt(num_question)?.text = "✓"
                            }else{
                                btnOtv1.setBackgroundColor(Color.parseColor("#fa4b32"))
                                info_questions[num_question] = 2
                                tabLayout.getTabAt(num_question)?.text = "X"
                            }
                        }
                        2 ->{
                            if(btnOtv2.text.equals(correctOtv)){
                                btnOtv2.setBackgroundColor(Color.parseColor("#4baf32"))
                                info_questions[num_question] = 1
                                tabLayout.getTabAt(num_question)?.text = "✓"
                            }else{
                                btnOtv2.setBackgroundColor(Color.parseColor("#fa4b32"))
                                info_questions[num_question] = 2
                                tabLayout.getTabAt(num_question)?.text = "X"
                            }
                        }
                        3 ->{
                            if(btnOtv3.text.equals(correctOtv)){
                                btnOtv3.setBackgroundColor(Color.parseColor("#4baf32"))
                                info_questions[num_question] = 1
                                tabLayout.getTabAt(num_question)?.text = "✓"
                            }else{
                                btnOtv3.setBackgroundColor(Color.parseColor("#fa4b32"))
                                info_questions[num_question] = 2
                                tabLayout.getTabAt(num_question)?.text = "X"
                            }
                        }
                        4 ->{
                            if(btnOtv4.text.equals(correctOtv)){
                                btnOtv4.setBackgroundColor(Color.parseColor("#4baf32"))
                                info_questions[num_question] = 1
                                tabLayout.getTabAt(num_question)?.text = "✓"
                            }else{
                                btnOtv4.setBackgroundColor(Color.parseColor("#fa4b32"))
                                info_questions[num_question] = 2
                                tabLayout.getTabAt(num_question)?.text = "X"
                            }
                        }
                    }
                    btnConfirm.setBackgroundColor(Color.parseColor("#9d9d9d"))
                    btnConfirm.setTextColor(Color.parseColor("#d2d2d2"))
                    btnConfirm.isClickable = false
                    btnOtv1.isClickable = false
                    btnOtv2.isClickable = false
                    btnOtv3.isClickable = false
                    btnOtv4.isClickable = false
                }else{
                    Log.d("FFF","Выберите ответ")
                }
            }
        }
    }
}