package com.vr.app.sh.ui.profile.view

import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.WindowCompat
import androidx.core.view.doOnLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.vr.app.sh.R

class MyProfile : AppCompatActivity() {

    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        WindowCompat.setDecorFitsSystemWindows(window,false)
        window.statusBarColor = Color.TRANSPARENT

        myProfileInfo()
    }

    private fun myProfileInfo(){

        val backgroundView = findViewById<CoordinatorLayout>(R.id.backgroundView)
        val viewBottomSheet: ConstraintLayout = findViewById(R.id.btn_sheet_profile_info)
        mBottomSheetBehavior = BottomSheetBehavior.from(viewBottomSheet)
        mBottomSheetBehavior.isHideable = false

        val bgViewBottom = findViewById<View>(R.id.bg_view_bottom)
        val heightStatusBar = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25.toFloat(), this.resources.displayMetrics).toInt()
        val heightTop = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40.toFloat(), this.resources.displayMetrics).toInt()


        viewBottomSheet.layoutParams.height = 1200

        backgroundView.doOnLayout {
            mBottomSheetBehavior.maxHeight = it.measuredHeight - heightStatusBar
        }

        bgViewBottom.doOnLayout {
            mBottomSheetBehavior.peekHeight = it.measuredHeight + heightTop
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }
}