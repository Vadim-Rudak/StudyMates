package com.vr.app.sh.ui.tests.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.vr.app.sh.domain.model.Question
import com.vr.app.sh.ui.tests.view.test.FragmentQuestion

class ActiveTestAdapter(
    fa: FragmentActivity,
    private var listQuestions:List<Question>,
    private var infoQuestions:Array<Int>,
    private var tabLayout: TabLayout
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = listQuestions.size
    override fun createFragment(position: Int): Fragment = FragmentQuestion(position, listQuestions,infoQuestions,tabLayout)
}