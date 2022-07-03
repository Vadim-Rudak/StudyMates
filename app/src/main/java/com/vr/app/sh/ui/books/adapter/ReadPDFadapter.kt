package com.vr.app.sh.ui.books.adapter

import android.graphics.pdf.PdfRenderer
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.vr.app.sh.ui.books.view.FragmentPagePDF

class ReadPDFadapter(fm: FragmentManager, var num_pages:Int, private val pdfRenderer: PdfRenderer) : FragmentPagerAdapter(fm) {

//    override fun getItemCount(): Int {
//        return num_pages
//    }
//
//    override fun createFragment(position: Int): Fragment = PagePDF(position,path)

    override fun getPageTitle(position: Int): CharSequence? {
        return "Страница ${position}"
    }

    override fun getCount(): Int {
        return num_pages
    }

    override fun getItem(position: Int): Fragment {
        return FragmentPagePDF(position,pdfRenderer)
    }

}