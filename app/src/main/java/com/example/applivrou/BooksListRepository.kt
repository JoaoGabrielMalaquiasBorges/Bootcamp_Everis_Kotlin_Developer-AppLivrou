package com.example.applivrou

import com.example.applivrou.model.Book
import com.example.applivrou.model.Singleton

class BooksListRepository() {
  private val googleBooksApiClient = GoogleBooksApiClient()

  private val booksList: ArrayList<Book> = ArrayList()
  
  fun getSamplesOfCategory(category: String): ArrayList<Book> {
    val helperSQLite = Singleton.instance.helperSQLite

    booksList?.clear()

    booksList.addAll(helperSQLite?.getBooks(category) ?: booksList)
    
    if (booksList.isEmpty()) {
      val response = googleBooksApiClient.searchForBooksOfCategory(category)

      helperSQLite?.saveBooks(response, category)

      booksList.addAll(helperSQLite?.getBooks(category) ?: booksList)
    }

    return booksList
  }
}    
