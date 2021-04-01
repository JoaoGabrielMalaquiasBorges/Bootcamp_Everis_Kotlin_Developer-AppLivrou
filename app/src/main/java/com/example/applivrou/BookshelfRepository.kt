package com.example.applivrou

import android.app.Application

class BookshelfRepository() : Application() {
  private var bookshelfDB: SQLiteDB? = null

  override fun onCreate() {
    super.onCreate()
    bookshelfDB = SQLiteDB(this)
  }

  // private val webService = WebService()
  private val booksList: ArrayList<Book> = ArrayList()
  
  fun getSamplesOfCategory(category: String): ArrayList<Book> {
    booksList?.clear() ?: booksList
    booksList.addAll(bookshelfDB?.getBooks(category) ?: arrayListOf())
    
    /*if (booksList.isEmpty()) {
      val response = webService.makeResquest()
      bookshelfDB.save(booksList)
      return bookshelfDB.getBooks(category)
    }*/

    return booksList
  }
}    
