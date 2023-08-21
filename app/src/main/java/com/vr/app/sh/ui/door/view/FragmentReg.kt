package com.vr.app.sh.ui.door.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vr.app.sh.R

class FragmentReg(val numPage:Int) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = when(numPage){
            1->inflater.inflate(R.layout.fragment_school_info, container, false)
            2->inflater.inflate(R.layout.fragment_add_user_photo, container, false)
            else->inflater.inflate(R.layout.fragment_as_for_me, container, false)
        }
        return view
    }
}