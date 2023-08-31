package com.vr.app.sh.ui.door.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.vr.app.sh.R
import com.vr.app.sh.ui.other.photoPicker.BottomSheetPickPhoto

class FragmentReg(val numPage:Int) : Fragment() {

    var gender = "man"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = when(numPage){
            1->inflater.inflate(R.layout.fragment_school_info, container, false)
            2->inflater.inflate(R.layout.fragment_create_login, container, false)
            3->inflater.inflate(R.layout.fragment_add_user_photo, container, false)
            else->inflater.inflate(R.layout.fragment_as_for_me, container, false)
        }
        when(numPage){
            1->{

            }
            2->{

            }
            3->{
                val pickPhoto = view.findViewById<ShapeableImageView>(R.id.reg_fr3_pick_photo)
                pickPhoto.setOnClickListener{
                    val placeReg = activity?.findViewById<CoordinatorLayout>(R.id.place_reg)
                    val viewBottomSheet = activity?.findViewById<LinearLayout>(R.id.sheet_pick_photo)
                    val bottomSheetPickPhoto = BottomSheetPickPhoto(requireContext(), viewBottomSheet!!, placeReg!!.height)
                    bottomSheetPickPhoto.see()
                    bottomSheetPickPhoto.onePhoto.observe(viewLifecycleOwner){
                        Glide.with(requireContext()).load(it).into(pickPhoto)
                    }
                }
            }
            else->{
                val btnMan = view.findViewById<AppCompatButton>(R.id.reg_btn_man)
                val btnWoman = view.findViewById<AppCompatButton>(R.id.reg_btn_women)
                btnMan.setOnClickListener {
                    if (gender != "man"){
                        gender = "man"
                        btnMan.setBackgroundResource(R.drawable.btn_gender_bg_true)
                        btnWoman.setBackgroundResource(R.drawable.btn_gender_bg_false)
                        btnMan.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                        btnWoman.setTextColor(ContextCompat.getColor(requireContext(),R.color.gray))
                    }
                }
                btnWoman.setOnClickListener {
                    if (gender != "woman"){
                        gender = "woman"
                        btnWoman.setBackgroundResource(R.drawable.btn_gender_bg_true)
                        btnMan.setBackgroundResource(R.drawable.btn_gender_bg_false)
                        btnWoman.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                        btnMan.setTextColor(ContextCompat.getColor(requireContext(),R.color.gray))
                    }
                }
            }
        }
        return view
    }
}