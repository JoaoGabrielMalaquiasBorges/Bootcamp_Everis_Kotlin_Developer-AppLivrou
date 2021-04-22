package com.example.applivrou

import com.example.applivrou.model.Book
import com.example.applivrou.model.Singleton

class BookshelfRepository() {
  private val googleBooksClient = GoogleBooksClient()

  private val booksList: ArrayList<Book> = ArrayList()
  
  fun getSamplesOfCategory(category: String): ArrayList<Book> {
    val bookshelfDB = Singleton.instance.bookshelfDB

    booksList?.clear()

    booksList.addAll(bookshelfDB?.getBooks(category) ?: booksList)
    
    if (booksList.isEmpty()) {
      val response = googleBooksClient.searchForBooksOfCategory(category)

      bookshelfDB?.saveBooks(response, category)

      booksList.addAll(bookshelfDB?.getBooks(category) ?: booksList)
    }

    return booksList
  }
}    
