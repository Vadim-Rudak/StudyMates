package com.vr.app.sh.ui.door.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.vr.app.sh.R
import com.vr.app.sh.data.repository.RegistrationInfo
import com.vr.app.sh.ui.other.cityPicker.BottomSheetPickCity
import com.vr.app.sh.ui.other.photoPicker.BottomSheetPickPhoto

class FragmentReg(val numPage:Int) : Fragment() {

    private val userProfile = RegistrationInfo.user
    private var bottomSheetPickPhoto:BottomSheetPickPhoto? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = when(numPage){
            1->inflater.inflate(R.layout.fragment_school_info, container, false)
            2->inflater.inflate(R.layout.fragment_create_login, container, false)
            3->inflater.inflate(R.layout.fragment_add_user_photo, container, false)
            else->inflater.inflate(R.layout.fragment_as_for_me, container, false)
        }
        when(numPage){
            1->{
                val school = userProfile.school

                val viewSchoolCity = view.findViewById<TextInputEditText>(R.id.reg_fr2_name_city)
                viewSchoolCity.doAfterTextChanged {
                    school.nameCity = it.toString()
                }
                val viewNameSchool = view.findViewById<TextInputEditText>(R.id.reg_fr2_name_school)
                viewNameSchool.doAfterTextChanged {
                    school.name = it.toString()
                }
                val viewNumClass = view.findViewById<TextInputEditText>(R.id.reg_fr2_num_class)
                viewNumClass.doAfterTextChanged {
                    if (it!!.isNotEmpty()){
                        school.numClass = it.toString().toInt()
                    }else{
                        school.numClass = 0
                    }
                }
                val viewEndSchool = view.findViewById<CheckBox>(R.id.checkBoxEndSchool)
                viewEndSchool.setOnCheckedChangeListener { buttonView, isChecked ->
                    school.endSchool = isChecked
                }
            }
            2->{
                val auth = userProfile.auth

                val viewLogin = view.findViewById<TextInputEditText>(R.id.reg_fr3_login)
                viewLogin.doAfterTextChanged {
                    auth.login = it.toString()
                }
                val viewPassword = view.findViewById<TextInputEditText>(R.id.reg_fr3_password)
                val viewPassword2 = view.findViewById<TextInputEditText>(R.id.reg_fr3_password2)
                viewPassword2.doAfterTextChanged {
                    if (it.toString()==viewPassword.text.toString()){
                        auth.password = it.toString()
                    }else{
                        auth.password = null
                    }
                }
            }
            3->{
                val placeReg = activity?.findViewById<CoordinatorLayout>(R.id.place_reg)
                val viewBottomSheet = activity?.findViewById<LinearLayout>(R.id.sheet_pick_photo)
                bottomSheetPickPhoto = BottomSheetPickPhoto(requireContext(), viewBottomSheet!!, placeReg!!.height)

                val pickPhoto = view.findViewById<ShapeableImageView>(R.id.reg_fr3_pick_photo)
                pickPhoto.setOnClickListener{
                    bottomSheetPickPhoto!!.see()
                }
                bottomSheetPickPhoto!!.onePhoto.observe(viewLifecycleOwner){
                    RegistrationInfo.user.photo.path = it
                    Glide.with(requireContext()).load(it).into(pickPhoto)
                }

            }
            else->{
                val viewName = view.findViewById<TextInputEditText>(R.id.reg_fr1_name)
                viewName.doAfterTextChanged {
                    userProfile.name = it.toString()
                }
                val viewLastName = view.findViewById<TextInputEditText>(R.id.reg_fr1_last_name)
                viewLastName.doAfterTextChanged {
                    userProfile.lastName = it.toString()
                }
                val btnMan = view.findViewById<AppCompatButton>(R.id.reg_btn_man)
                val btnWoman = view.findViewById<AppCompatButton>(R.id.reg_btn_women)
                btnMan.setOnClickListener {
                    if (userProfile.gender != "man"){
                        userProfile.gender = "man"
                        btnMan.setBackgroundResource(R.drawable.btn_gender_bg_true)
                        btnWoman.setBackgroundResource(R.drawable.btn_gender_bg_false)
                        btnMan.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                        btnWoman.setTextColor(ContextCompat.getColor(requireContext(),R.color.gray))
                    }
                }
                btnWoman.setOnClickListener {
                    if (userProfile.gender != "woman"){
                        userProfile.gender = "woman"
                        btnWoman.setBackgroundResource(R.drawable.btn_gender_bg_true)
                        btnMan.setBackgroundResource(R.drawable.btn_gender_bg_false)
                        btnWoman.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                        btnMan.setTextColor(ContextCompat.getColor(requireContext(),R.color.gray))
                    }
                }

                val layoutInputDateBirthday = view.findViewById<TextInputLayout>(R.id.layoutDateInput)
                val viewDateBirthday = view.findViewById<TextInputEditText>(R.id.reg_fr1_date)
                viewDateBirthday.focusable = View.NOT_FOCUSABLE
                viewDateBirthday.setOnClickListener {
                    selectDate(onClick = {
                        layoutInputDateBirthday.hint = ""
                        viewDateBirthday.setText(it)
                        RegistrationInfo.user.dateBirthday = it
                    })
                }

                val placeReg = activity?.findViewById<CoordinatorLayout>(R.id.place_reg)
                val viewBottomSheet = activity?.findViewById<LinearLayout>(R.id.bottom_sheet_city)
                val bottomSheetPickCity = BottomSheetPickCity(requireContext(),viewBottomSheet!!,placeReg!!.height)

                val layoutInputCity = view.findViewById<TextInputLayout>(R.id.layoutCityInput)
                val viewCity = view.findViewById<TextInputEditText>(R.id.reg_fr1_city)
                viewCity.focusable = View.NOT_FOCUSABLE
                viewCity.setOnClickListener {
                    bottomSheetPickCity.see()
                }
                bottomSheetPickCity.selectCity.observe(viewLifecycleOwner){
                    layoutInputCity.hint = ""
                    viewCity.setText(it)
                }
            }
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        if(numPage==3&&bottomSheetPickPhoto!=null){
            if (bottomSheetPickPhoto!!.visiblePicker()){
                Log.d("FFF","ok")
                bottomSheetPickPhoto!!.updatePhotos()
            }
        }
    }

    private fun selectDate(onClick:(String) -> Unit){
        val picker = MaterialDatePicker
            .Builder
            .datePicker()
            .build()

        picker.addOnPositiveButtonClickListener {
            onClick(picker.headerText)
        }

        picker.show((context as AppCompatActivity).supportFragmentManager,"d")
    }
}