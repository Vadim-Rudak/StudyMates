package com.vr.app.sh.ui.books.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vr.app.sh.R
import com.vr.app.sh.domain.model.Book

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var listener: Listener? = null
    lateinit var bookList:List<Book>

    fun setBook(books: List<Book>) {
        this.bookList = books
    }

    interface Listener {
        fun Clicked(pos_book: Int, name_book:String, id_book: Int)
        fun LongClicked(pos_book: Int, name_book:String, id_book: Int)
    }

    override fun getItemCount(): Int = bookList.size


    fun setListener(listener: Listener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_button_sub, parent, false) as CardView
        return ViewHolder(cv)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.cardView
        val imageView = cardView.findViewById<View>(R.id.image_btn_sub) as ImageView
        if(!bookList[position].imagebook.equals("")){
            imageView.setImageBitmap(Base64ToBitmap(bookList[position].imagebook))
        }
        val id_book = cardView.findViewById<View>(R.id.text_id_book) as TextView
        id_book.text = bookList[position].id.toString()
        val name_book = cardView.findViewById<View>(R.id.text_btn_sub) as TextView
        name_book.text = bookList[position].namebook

        cardView.setOnClickListener {
            if (listener != null) {
                listener!!.Clicked(position,name_book.text.toString(), Integer.parseInt(id_book.text.toString()))
            }
        }

        cardView.setOnLongClickListener {
            if (listener != null) {
                listener!!.LongClicked(position,name_book.text.toString(), Integer.parseInt(id_book.text.toString()))
            }
            true
        }
    }

    fun Base64ToBitmap(myImageData: String): Bitmap? {
        val imageAsBytes = Base64.decode(myImageData.toByteArray(), Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size)
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}