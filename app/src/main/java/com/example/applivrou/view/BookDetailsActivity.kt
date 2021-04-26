package com.example.applivrou.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.applivrou.R
import com.example.applivrou.model.Book

class BookDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val bookDetails: Book = intent.getParcelableExtra("BOOK_DETAILS")!!

        setSupportActionBar(findViewById(R.id.topAppBar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bookTitle: TextView = findViewById(R.id.book_title)
        val bookAuthor: TextView = findViewById(R.id.book_author)
        val bookDescription: TextView = findViewById(R.id.book_description)
        val bookCover: ImageView = findViewById(R.id.book_cover)

        bookTitle.text = bookDetails.title
        bookAuthor.text = bookDetails.author
        bookDescription.text = bookDetails.description

        bookCover.load(bookDetails.cover) {
            placeholder(R.drawable.book_cover_placeholder)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}