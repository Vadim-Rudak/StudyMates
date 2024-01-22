package com.vr.app.sh.ui.books.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vr.app.sh.ui.books.view.FragmentSelectBook

class ViewPager2Adapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int = 11

    override fun createFragment(position: Int): Fragment = FragmentSelectBook(position+1)
}