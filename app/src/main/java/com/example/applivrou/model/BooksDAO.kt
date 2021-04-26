package com.example.applivrou.model

interface BooksDAO {
    fun getBooks(category: String): ArrayList<Book>
}