package com.vr.app.sh.ui.books.view

import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vr.app.sh.R
import com.vr.app.sh.ui.books.adapter.ReadPDFAdapter
import java.io.File

class ReadPDF : AppCompatActivity() {

    private var pdfRenderer: PdfRenderer? = null
    private var fileDescriptor: ParcelFileDescriptor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_pdf)

        val path = intent.getStringExtra("path").toString()
        val file = File(path)
        fileDescriptor = ParcelFileDescriptor.open(file,ParcelFileDescriptor.MODE_READ_ONLY)
        pdfRenderer = PdfRenderer(fileDescriptor!!)
        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
        val viewTitle = findViewById<TextView>(R.id.viewTitle)
        viewTitle.text = file.nameWithoutExtension

        val viewPager = findViewById<ViewPager2>(R.id.pager)
        viewPager.adapter = ReadPDFAdapter(this,pdfRenderer!!.pageCount,pdfRenderer!!)

        val tabLayout = findViewById<TabLayout>(R.id.tabs_open_pdf)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getString(R.string.tab_book_page) + " $position"
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        pdfRenderer?.close()
        fileDescriptor?.close()
    }
}