package com.example.applivrou

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request

class GoogleBooks() {
    private val APPLICATION_API_KEY: String = "AIzaSyDUCjMkRDm3h44xSbQ2BQ2-fpLjdyZ_yVM"
    private val booksList: ArrayList<JsonObject> = ArrayList()
    private val httpClient = OkHttpClient()

    fun searchForBooksOfCategory(category: String): ArrayList<JsonObject> {
        booksList?.clear()

        val endpoint = "https://www.googleapis.com/books/v1/volumes?q=subject:${category}&printType=books&maxResults=20&key=${APPLICATION_API_KEY}"
        val request = Request.Builder().url(endpoint).build()
        val response = httpClient.newCall(request).execute()
        val result = response.body?.string()

        val books = JsonParser.parseString(result).asJsonObject.getAsJsonArray("items").iterator()

        var id: String
        var title: String
        var author: String
        var releaseDate: String
        var publisher: String
        var description: String
        var cover: String
        
        var maxAmount = 20
        var book: JsonElement
        
        while (maxAmount >= 1) {
            book = books.next()
            books.remove()

            id = 
                book.asJsonObject.get("id")?.asString
                    ?: continue
            title =
                book.asJsonObject.get("volumeInfo")?.asJsonObject?.get("title")?.asString
                    ?: continue
            author =
                book.asJsonObject.get("volumeInfo")?.asJsonObject?.get("authors")?.asJsonArray?.get(0)?.asString
                    ?: continue
            releaseDate =
                book.asJsonObject.get("volumeInfo")?.asJsonObject?.get("publishedDate")?.asString
                    ?: continue
            publisher =
                book.asJsonObject.get("volumeInfo")?.asJsonObject?.get("publisher")?.asString
                    ?: continue
            description =
                book.asJsonObject.get("volumeInfo")?.asJsonObject?.get("description")?.asString
                    ?: continue
            cover =
                book.asJsonObject.get("volumeInfo")?.asJsonObject?.get("imageLinks")?.asJsonObject?.get("thumbnail")?.asString
                    ?: continue

            val jsonObject = JsonObject()
            jsonObject.addProperty("id", id)
            jsonObject.addProperty("title", title)
            jsonObject.addProperty("author", author)
            jsonObject.addProperty("releaseDate", releaseDate)
            jsonObject.addProperty("publisher", publisher)
            jsonObject.addProperty("description", description)
            jsonObject.addProperty("cover", cover)
            
            booksList.add(jsonObject)

            maxAmount--
        }
        
        return booksList
    }
}
