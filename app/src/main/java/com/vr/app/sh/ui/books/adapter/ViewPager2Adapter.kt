package com.vr.app.sh.ui.books.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vr.app.sh.ui.books.view.FragmentSubjectsClass

class ViewPager2Adapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int {
        return 11
    }

    override fun createFragment(position: Int): Fragment {
        return FragmentSubjectsClass(position+1)
    }
}