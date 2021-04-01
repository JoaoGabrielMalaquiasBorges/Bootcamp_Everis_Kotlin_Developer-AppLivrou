package com.example.applivrou

interface BookshelfDAO {
    fun getBooks(category: String): ArrayList<Book>
}