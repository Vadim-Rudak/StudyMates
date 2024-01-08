package com.vr.app.sh.ui.books.adapter

import android.graphics.pdf.PdfRenderer
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vr.app.sh.ui.books.view.FragmentPagePDF

class ReadPDFAdapter(fm: FragmentActivity, private var numPages:Int, private val pdfRenderer: PdfRenderer) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = numPages

    override fun createFragment(position: Int): Fragment = FragmentPagePDF(position,pdfRenderer)
}