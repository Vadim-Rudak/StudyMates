package com.vr.app.sh.ui.other.photoPicker

import android.content.Context
import android.graphics.drawable.Drawable
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

        if (position == 0){
            image.setImageResource(R.drawable.take_photo_in_picker)
        }else{
            Glide.with(context)
                .load(listPhotos[position-1])
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(image)
        }

    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}