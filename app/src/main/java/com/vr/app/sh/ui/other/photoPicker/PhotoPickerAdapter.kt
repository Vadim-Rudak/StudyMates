package com.vr.app.sh.ui.other.photoPicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView
import com.vr.app.sh.R

class PhotoPickerAdapter(val context:Context, private val selectMorePhotos:Boolean): RecyclerView.Adapter<PhotoPickerAdapter.ViewHolder>() {

    var LIST_USE_PHOTO:ArrayList<Int> = ArrayList()
    lateinit var listPhotos:ArrayList<String>

    fun setPhotos(items: ArrayList<String>?) {
        this.listPhotos = items!!
        notifyDataSetChanged()
    }

    fun getOnePhoto():String{
        return listPhotos[LIST_USE_PHOTO[0]-1]
    }

    fun getMorePhoto():ArrayList<String>{
        val selectPhoto:ArrayList<String> = ArrayList()
        for (i in LIST_USE_PHOTO){
            selectPhoto.add(listPhotos[i-1])
        }
        return selectPhoto
    }

    override fun getItemCount(): Int = listPhotos.size + 1 // + 1, because one item in list is use to take photo

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
        val viewNumUsePhoto = cardView.findViewById<TextView>(R.id.num_use_photo)

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
                viewNumUsePhoto.visibility = View.VISIBLE
                viewNumUsePhoto.text = (LIST_USE_PHOTO.indexOf(position) + 1).toString()
            }else{
                imgCheckOK.visibility = View.VISIBLE
            }
        }else{
            checkUsePhoto.setImageResource(R.drawable.img_not_use_photo)
            if (selectMorePhotos){
                viewNumUsePhoto.visibility = View.GONE
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