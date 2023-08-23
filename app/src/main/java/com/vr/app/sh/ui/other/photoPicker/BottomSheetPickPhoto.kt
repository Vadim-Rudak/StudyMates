package com.vr.app.sh.ui.other.photoPicker

import android.content.Context
import android.content.res.Resources
import android.database.Cursor
import android.provider.MediaStore
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.vr.app.sh.R
import java.util.ArrayList

class BottomSheetPickPhoto(val context: Context, private val viewBottomSheet:LinearLayout, private val windowHeight:Int) {

    val defaultHeight = windowHeight/2
    val heightTopLine = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12.toFloat(), context.resources.displayMetrics).toInt()
    val heightBottomLine = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60.toFloat(), context.resources.displayMetrics).toInt()
    val adapter = PhotoPickerAdapter(context)


    fun see(){
        val pickPhotoBottomSheet = BottomSheetBehavior.from(viewBottomSheet)
        pickPhotoBottomSheet.peekHeight = defaultHeight
        pickPhotoBottomSheet.maxHeight = windowHeight

        val recyclerView = viewBottomSheet.findViewById<RecyclerView>(R.id.list_photos)
        val params = recyclerView.layoutParams
        params.height = windowHeight/2 - heightTopLine - heightBottomLine
        recyclerView.layoutParams = params


        recyclerView.layoutManager = GridLayoutManager(context,3)
        adapter.setPhotos(getGalleryImages(context))
        recyclerView.adapter = adapter

        pickPhotoBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED

        pickPhotoBottomSheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {}

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if (slideOffset>0){
                    val  dynamicParams = recyclerView.layoutParams
                    dynamicParams.height = (defaultHeight + defaultHeight*slideOffset).toInt() - heightTopLine - heightBottomLine
                    recyclerView.layoutParams = dynamicParams
                }
            }
        })
    }

    fun getGalleryImages(context: Context): ArrayList<String> {
        val galleryImageUrls: ArrayList<String>
        val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID) //get all columns of type images

        val orderBy = MediaStore.Images.Media.DATE_TAKEN //order data by date
        val imagecursor: Cursor = context.getContentResolver().query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
            null, "$orderBy DESC"
        )!! //get all data in Cursor by sorting in DESC order
        galleryImageUrls = ArrayList()
        imagecursor.moveToFirst()
        while (!imagecursor.isAfterLast()) {
            val dataColumnIndex: Int =
                imagecursor.getColumnIndex(MediaStore.Images.Media.DATA) //get column index
            galleryImageUrls.add(imagecursor.getString(dataColumnIndex)) //get Image from column index
            imagecursor.moveToNext()
        }
        return galleryImageUrls
    }
}