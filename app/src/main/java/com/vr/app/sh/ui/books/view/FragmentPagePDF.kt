package com.vr.app.sh.ui.books.view

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.vr.app.sh.R

class FragmentPagePDF(var pageNumber: Int, private val pdfRenderer:PdfRenderer) : Fragment() {

    lateinit var image:ImageView
    lateinit var bt:Bitmap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_page_pdf, container, false)
        image = view.findViewById(R.id.imageView)
        readPage()
        return view
    }

    private fun readPage(){
        val rendererPage = pdfRenderer.openPage(pageNumber)
        bt = Bitmap.createBitmap(
            resources.displayMetrics.densityDpi / 50 * rendererPage.width,
            resources.displayMetrics.densityDpi / 50 * rendererPage.height,
            Bitmap.Config.ARGB_8888
        )
        rendererPage.render(
            bt, null, null,
            PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY
        )
        rendererPage.close()
        image.setImageBitmap(bt)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bt.recycle()
    }
}