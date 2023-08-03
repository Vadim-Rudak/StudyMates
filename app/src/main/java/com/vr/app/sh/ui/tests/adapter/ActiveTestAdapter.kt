package com.vr.app.sh.ui.tests.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.vr.app.sh.data.model.Question
import com.vr.app.sh.data.repository.DAOQuestions
import com.vr.app.sh.ui.tests.view.test.FragmentQuestion

class ActiveTestAdapter(fm: FragmentManager, var listQuestions:List<Question>,var info_questions:Array<Int>, var tabLayout: TabLayout) : FragmentPagerAdapter(fm) {
    override fun getPageTitle(position: Int): CharSequence? {
        return "Вопрос ${position + 1}"
    }

    override fun getCount(): Int {
        return  listQuestions.size
    }

    override fun getItem(position: Int): Fragment {
        return FragmentQuestion(position, listQuestions, info_questions,tabLayout)
    }
}