class BookshelfRepository() {
  private val bookshelfDB = SQLiteDatabase()
  private val webService = WebService()
  private val booksList: ArrayList<Book> = ArrayList()
  
  fun getSamplesOfCategory(category: String): ArrayList<Book> {
    booksList.getOrNull(0)?.clear ?: booksList
    booksList.addAll(bookshelfDB.getBooks(category))
    
    jf (booksList.isEmpty()) {
      val response = webService.makeResquest()
      bookshelfDB.save(booksList)
      return bookshelfDB.getBooks(category)
    } else {
      return booksList
    }
  }
}    
