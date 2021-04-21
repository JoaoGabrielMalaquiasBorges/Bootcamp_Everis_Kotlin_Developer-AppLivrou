package com.example.applivrou.view

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applivrou.Book
import com.example.applivrou.R

class Adapter(private val onClickListener: (bookDetails: Parcelable) -> Unit) :
      RecyclerView.Adapter<Adapter.ViewHolder>() {
    private val list: ArrayList<Book> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setProperties(list[position])
    }

    fun updateList(list: ArrayList<Book>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookTitle: TextView = itemView.findViewById(R.id.title)
        private val bookAuthor: TextView = itemView.findViewById(R.id.author)
        private val bookCover: ImageView = itemView.findViewById(R.id.cover)
        // private val bookDescription: TextView = itemView.findViewById(R.id.description)

        /*init {
            itemView.setOnClickListener {
                onClickListener()
            }
        }*/

        fun setProperties(book: Book) {
            bookTitle.text = book.title
            bookAuthor.text = book.author
            bookCover.setImageBitmap(book.cover)

            itemView.setOnClickListener {
                onClickListener(book)
            }

            /*val url = URL(book.cover)
            val bmp: Bitmap? = BitmapFactory.decodeStream(url.openConnection().getInputStream())*/

            // bookDescription.text = book.description
        }
    }
}