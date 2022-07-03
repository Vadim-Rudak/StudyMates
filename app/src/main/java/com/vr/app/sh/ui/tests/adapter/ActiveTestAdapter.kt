package com.vr.app.sh.ui.tests.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.vr.app.sh.domain.repository.QuestionsRepo
import com.vr.app.sh.ui.books.view.FragmentPagePDF
import com.vr.app.sh.ui.tests.view.test.FragmentQuestion

class ActiveTestAdapter(fm: FragmentManager, var questionsRepo: QuestionsRepo,var info_questions:Array<Int>, var tabLayout: TabLayout) : FragmentPagerAdapter(fm) {
    override fun getPageTitle(position: Int): CharSequence? {
        return "Вопрос ${position + 1}"
    }

    override fun getCount(): Int {
        return questionsRepo.getQuestions().size
    }

    override fun getItem(position: Int): Fragment {
        return FragmentQuestion(position, questionsRepo, info_questions,tabLayout)
    }
}