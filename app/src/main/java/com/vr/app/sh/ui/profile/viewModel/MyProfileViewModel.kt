package com.vr.app.sh.ui.profile.viewModel

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MyProfileViewModel(resources: Resources):ViewModel() {

    private lateinit var profileInfoBottomSheet:BottomSheetBehavior<*>
    val heightBottomSheet = MutableLiveData<Int>()
    private val heightStatusBar = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25.toFloat(), resources.displayMetrics).toInt()
    private val heightTopDefault = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40.toFloat(), resources.displayMetrics).toInt()

    fun createBottomSheet(view:View){
        profileInfoBottomSheet = BottomSheetBehavior.from(view)
        profileInfoBottomSheet.isHideable = false
    }

    fun setBottomSheetMaxHeight(heightWindow:Int){
        profileInfoBottomSheet.maxHeight = heightWindow - heightStatusBar
    }

    fun setBottomSheetDefaultHeight(height:Int){
        heightBottomSheet.postValue(height + heightTopDefault)
    }

    fun seeBottomSheet(defaultHeight:Int){
        profileInfoBottomSheet.apply {
            peekHeight = defaultHeight
            BottomSheetBehavior.STATE_COLLAPSED
        }
    }
}