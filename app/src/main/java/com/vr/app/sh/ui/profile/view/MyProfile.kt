package com.vr.app.sh.ui.profile.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.WindowCompat
import androidx.core.view.doOnLayout
import androidx.lifecycle.ViewModelProvider
import com.vr.app.sh.R
import com.vr.app.sh.app.App
import com.vr.app.sh.ui.base.MyProfileViewModelFactory
import com.vr.app.sh.ui.profile.viewModel.MyProfileViewModel
import dev.chrisbanes.insetter.applyInsetter

class MyProfile : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var factory: MyProfileViewModelFactory

    lateinit var viewModel: MyProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        WindowCompat.setDecorFitsSystemWindows(window,false)
        window.statusBarColor = Color.TRANSPARENT

        (applicationContext as App).appComponent.injectMyProfile(this)

        viewModel = ViewModelProvider(this, factory)
            .get(MyProfileViewModel::class.java)

        val viewBottomSheet = findViewById<ConstraintLayout>(R.id.btn_sheet_profile_info)
        viewModel.createBottomSheet(viewBottomSheet)

        val backgroundView = findViewById<CoordinatorLayout>(R.id.backgroundView)
        backgroundView.doOnLayout {
            viewModel.setBottomSheetMaxHeight(it.measuredHeight)
        }

        backgroundView.applyInsetter {
            type(navigationBars = true){
                margin()
            }
        }

        val bgViewBottom = findViewById<View>(R.id.bg_view_bottom)
        bgViewBottom.doOnLayout {
            viewModel.setBottomSheetDefaultHeight(it.height)
        }

        viewModel.heightBottomSheet.observe(this){
            if(viewBottomSheet.layoutParams.height<it){
                viewBottomSheet.layoutParams.height = it
            }
            viewModel.seeBottomSheet(it)
        }
    }
}