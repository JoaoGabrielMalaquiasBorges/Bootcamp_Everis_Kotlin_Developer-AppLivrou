package com.example.applivrou.view

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.applivrou.R
import com.example.applivrou.model.Book

class BookDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val bookDetails: Book? = intent.getParcelableExtra("BOOK_DETAILS")
        val bookCover: ImageView = findViewById(R.id.book_cover)

        bookCover.load(bookDetails?.cover) {
            placeholder(R.drawable.book_cover_placeholder)
        }
    }
}