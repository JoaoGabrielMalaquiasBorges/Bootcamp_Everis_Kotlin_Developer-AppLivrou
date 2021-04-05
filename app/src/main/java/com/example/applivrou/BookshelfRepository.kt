package com.example.applivrou

class BookshelfRepository() {
  private val googleBooksClient = GoogleBooks()

  private val booksList: ArrayList<Book> = ArrayList()
  
  fun getSamplesOfCategory(category: String): ArrayList<Book> {
    val bookshelfDB = Singleton.instance.bookshelfDB
    booksList?.clear()
    booksList.addAll(bookshelfDB?.getBooks(category) ?: booksList)
    
    if (booksList.isEmpty()) {
      val response = googleBooksClient.searchForBooksOfCategory(category)
      bookshelfDB?.saveBooks(response, category)
      val x = bookshelfDB?.getBooks(category)
      booksList.addAll(x ?: booksList)
    }

    return booksList
  }
}    
