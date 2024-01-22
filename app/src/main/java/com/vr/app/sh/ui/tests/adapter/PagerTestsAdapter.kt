package com.vr.app.sh.ui.tests.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vr.app.sh.ui.tests.view.listTests.FragmentListTests

class PagerTestsAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 11

    override fun createFragment(position: Int): Fragment = FragmentListTests(position+1)
}