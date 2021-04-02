package com.example.applivrou

class GoogleBooks() {
  private val APPLICATION_API_KEY: String = ""
  val booksList: ArrayList<Book> = ArrayList() 
  
  /* fun searchForBooksOfCategory(category: String) {
    booksList.clear()
    
    val endpoint = "https://www.googleapis.com/books/v1/volumes?q=subject:${category}&printType=books&key=${APPLICATION_API_KEY}"
    val url = URL(endpoint)
    val urlConnection = (HttpsURLConnection) url.openConnection()
    val response = urlConnection.getInputStream()
    
    val booksJsonElements: ArrayList<JsonElement> = JsonParser.parseString(response).getAsJsonObject().getAsJsonArray("items").iterator()
    
    booksJsonElements.forEach { bookJsonElement ->
      val authorsList: ArrayList<String> = bookJsonElement.getAsJsonObject().get("volumeInfo").getAsJsonObject().get("authors").getAsJsonArray().iterator()
      
    booksList.add(
      Book(
        bookJsonElement.getAsJsonObject().get("volumeInfo").getAsJsonObject().get("title").getAsString(),
        authorsList[0],
        bookJsonElement.getAsJsonObject().get("volumeInfo").getAsJsonObject().get("publishedDate").getAsString(),
        bookJsonElement.getAsJsonObject().get("volumeInfo").getAsJsonObject().get("publisher").getAsString(),
        bookJsonElement.getAsJsonObject().get("volumeInfo").getAsJsonObject().get("description").getAsString(),
        bookJsonElement.getAsJsonObject().get("volumeInfo").getAsJsonObject().get("imageLinks").getAsString().getAsJsonObject().get("thumbnail").getAsString()
      )
    )
    
    return booksList
  } */
}
