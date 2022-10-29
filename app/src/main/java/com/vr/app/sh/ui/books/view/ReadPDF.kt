package com.vr.app.sh.ui.books.view

import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.vr.app.sh.R
import com.vr.app.sh.ui.books.adapter.ReadPDFadapter
import java.io.File

class ReadPDF : AppCompatActivity() {

    var pdfRenderer: PdfRenderer? = null
    var fileDescriptor: ParcelFileDescriptor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_pdf)

        val path = intent.getStringExtra("path").toString()
        val file = File(path)
        fileDescriptor = ParcelFileDescriptor.open(file,ParcelFileDescriptor.MODE_READ_ONLY)
        pdfRenderer = PdfRenderer(fileDescriptor!!)

        val viewPager = findViewById<ViewPager>(R.id.pager_questions)
        viewPager.adapter = ReadPDFadapter(supportFragmentManager,pdfRenderer!!.pageCount,pdfRenderer!!)

        val tabLayout = findViewById<TabLayout>(R.id.tabs_open_pdf)
        tabLayout.setupWithViewPager(viewPager)
//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.text = "Страница ${position}"
//        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        pdfRenderer?.close()
        fileDescriptor?.close()
    }
}