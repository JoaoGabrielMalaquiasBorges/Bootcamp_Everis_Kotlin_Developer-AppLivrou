package com.example.applivrou.view

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.applivrou.R
import com.example.applivrou.model.Book

class BooksListRecyclerViewAdapter(private val onClickListener: (bookDetails: Parcelable) -> Unit) :
      RecyclerView.Adapter<BooksListRecyclerViewAdapter.ViewHolder>() {
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

        fun setProperties(book: Book) {
            bookTitle.text = book.title
            bookAuthor.text = book.author

            bookCover.load(book.cover) {
                placeholder(R.drawable.book_cover_placeholder)
            }

            itemView.setOnClickListener {
                onClickListener(book)
            }
        }
    }
}