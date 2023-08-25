package com.vr.app.sh.ui.other.photoPicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView
import com.vr.app.sh.R

class PhotoPickerAdapter(val context:Context): RecyclerView.Adapter<PhotoPickerAdapter.ViewHolder>() {

    var selectMorePhotos = false
    var LIST_USE_PHOTO:ArrayList<Int> = ArrayList()
    lateinit var listPhotos:ArrayList<String>

    fun setPhotos(items: ArrayList<String>?) {
        this.listPhotos = items!!
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listPhotos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_pick_photo, parent, false) as CardView
        return ViewHolder(cv)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView

        val image = cardView.findViewById<ShapeableImageView>(R.id.Image_Add_Photo)
        val checkUsePhoto = cardView.findViewById<ImageView>(R.id.use_photo)
        val imgCheckOK = cardView.findViewById<ImageView>(R.id.img_ok)

        if (position == 0){
            image.setImageResource(R.drawable.take_photo_in_picker)
            checkUsePhoto.visibility = View.GONE
        }else{
            Glide.with(context)
                .load(listPhotos[position-1])
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(image)
        }

        if (LIST_USE_PHOTO.indexOf(position)!=-1){
            checkUsePhoto.setImageResource(R.drawable.img_use_photo)
            if (selectMorePhotos){

            }else{
                imgCheckOK.visibility = View.VISIBLE
            }
        }else{
            checkUsePhoto.setImageResource(R.drawable.img_not_use_photo)
            if (selectMorePhotos){

            }else{
                imgCheckOK.visibility = View.GONE
            }
        }



        cardView.setOnClickListener {
            if (position==0){
                //take photo

            }else{

                if (LIST_USE_PHOTO.indexOf(position)!=-1){
                    LIST_USE_PHOTO.removeAt(LIST_USE_PHOTO.indexOf(position))
                    notifyDataSetChanged()
                }else{
                    if(selectMorePhotos){
                        usePhotoAndLimit(10,position)
                    }else{
                        usePhotoAndLimit(1,position)
                    }
                }



            }

        }
    }

    fun usePhotoAndLimit(limit:Int,position: Int){
        if (LIST_USE_PHOTO.size<limit){
            LIST_USE_PHOTO.add(position)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}