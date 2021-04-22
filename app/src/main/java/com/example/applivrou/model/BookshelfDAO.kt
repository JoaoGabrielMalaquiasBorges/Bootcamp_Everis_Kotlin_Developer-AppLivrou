package com.example.applivrou.model

interface BookshelfDAO {
    fun getBooks(category: String): ArrayList<Book>
}