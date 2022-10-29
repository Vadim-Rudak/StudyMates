package com.vr.app.sh.ui.tests.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vr.app.sh.ui.tests.view.listTests.FragmentListTests

class PagerTestsAdapter(fm: FragmentActivity): FragmentStateAdapter(fm) {

    override fun getItemCount(): Int {
        return 11
    }

    override fun createFragment(position: Int): Fragment {
        return FragmentListTests(position+1)
    }
}