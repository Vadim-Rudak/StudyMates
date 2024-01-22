package com.vr.app.sh.ui.timeTable.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vr.app.sh.ui.timeTable.view.DayFragment

class PagerTimeTableAdapter(fm: FragmentActivity): FragmentStateAdapter(fm) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment = DayFragment(position)
}