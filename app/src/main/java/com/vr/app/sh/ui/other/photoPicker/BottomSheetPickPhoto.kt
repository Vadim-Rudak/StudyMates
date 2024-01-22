package com.vr.app.sh.ui.other.photoPicker

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.provider.MediaStore
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.vr.app.sh.R

class BottomSheetPickPhoto(val context: Context, private val viewBottomSheet:LinearLayout, private val windowHeight:Int) {

    private val pickPhotoBottomSheet = BottomSheetBehavior.from(viewBottomSheet)
    private var pickMorePhoto = false
    val onePhoto = MutableLiveData<String>()
    private val morePhotos = MutableLiveData<ArrayList<String>>()
    val defaultHeight = windowHeight/2
    val heightTopLine = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12.toFloat(), context.resources.displayMetrics).toInt()
    val heightBottomLine = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60.toFloat(), context.resources.displayMetrics).toInt()
    val adapter = PhotoPickerAdapter(context,pickMorePhoto)


    fun see(){
        pickPhotoBottomSheet.peekHeight = defaultHeight
        pickPhotoBottomSheet.maxHeight = windowHeight

        val recyclerView = viewBottomSheet.findViewById<RecyclerView>(R.id.list_photos)
        val btnAddPhoto = viewBottomSheet.findViewById<Button>(R.id.btn_add_photo)
        editButton(viewButton = btnAddPhoto,click = false)

        val params = recyclerView.layoutParams
        params.height = windowHeight/2 - heightTopLine - heightBottomLine
        recyclerView.layoutParams = params

        recyclerView.layoutManager = GridLayoutManager(context,3)
        adapter.setPhotos(getGalleryImages(context))
        recyclerView.adapter = adapter

        adapter.setListener(object : PhotoPickerAdapter.Listener{
            override fun clickItem(numSelectedPhotos: Int) {
                if (numSelectedPhotos==0){
                    editButton(viewButton = btnAddPhoto,click = false)
                }else{
                    editButton(viewButton = btnAddPhoto,click = true)
                }
            }

            override fun takePhoto() {
                Intent(context,CameraAct::class.java).also {
                    context.startActivity(it)
                }
            }
        })

        btnAddPhoto.setOnClickListener {
            if (adapter.numSelectedPhotos()!=0){
                if (pickMorePhoto){
                    morePhotos.postValue(adapter.getMorePhoto())
                }else{
                    onePhoto.postValue(adapter.getOnePhoto())
                }
            }
            pickPhotoBottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
        }

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

    fun updatePhotos(){
        adapter.setPhotos(getGalleryImages(context))
    }

    fun visiblePicker():Boolean = pickPhotoBottomSheet.peekHeight>0

    private fun editButton(viewButton: Button,click:Boolean){
        viewButton.apply {
            if (click){
                setText(R.string.pick_photo_btn_add)
                setTextColor(context.getColor(R.color.white))
                background.setTint(context.getColor(R.color.first_color))
            }else{
                setText(R.string.pick_photo_btn_cansel)
                setTextColor(context.getColor(R.color.first_color))
                background.setTint(context.getColor(R.color.gray2))
            }
        }
    }

    private fun getGalleryImages(context: Context): ArrayList<String> {
        val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID) //get all columns of type images

        val orderBy = MediaStore.Images.Media.DATE_TAKEN //order data by date
        val imagecursor: Cursor = context.getContentResolver().query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
            null, "$orderBy DESC"
        )!! //get all data in Cursor by sorting in DESC order
        val galleryImageUrls: ArrayList<String> = ArrayList()
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