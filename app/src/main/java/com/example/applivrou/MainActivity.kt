package com.example.applivrou

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.books_shelf)

        if (savedInstanceState == null) {
            val booksListViewPagerFragment = BooksListViewPagerFragment()
            val booksListViewPagerFragmentManager = supportFragmentManager.beginTransaction()

            booksListViewPagerFragmentManager.add(R.id.view, booksListViewPagerFragment)
            booksListViewPagerFragmentManager.commit()
        }
    }
}