package com.example.applivrou.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.applivrou.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val booksListViewPagerFragment = BooksListViewPagerFragment()
            val booksListViewPagerFragmentManager = supportFragmentManager.beginTransaction()

            booksListViewPagerFragmentManager.add(R.id.container, booksListViewPagerFragment)
            booksListViewPagerFragmentManager.commit()
        }
    }
}