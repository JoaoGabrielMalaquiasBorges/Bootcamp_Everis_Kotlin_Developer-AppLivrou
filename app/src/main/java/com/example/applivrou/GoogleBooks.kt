package com.example.applivrou

import android.graphics.BitmapFactory
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL

class GoogleBooks() {
    private val APPLICATION_API_KEY: String = "AIzaSyDUCjMkRDm3h44xSbQ2BQ2-fpLjdyZ_yVM"
    private val booksList: ArrayList<Book> = ArrayList()

    fun searchForBooksOfCategory(category: String): ArrayList<Book> {
        booksList.clear()

        val url = "https://www.googleapis.com/books/v1/volumes?q=subject:${category}&printType=books&maxResults=20&key=${APPLICATION_API_KEY}"

        val httpClient = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val response = httpClient.newCall(request).execute()
        val result = response.body?.string()

        val booksJsonElements = JsonParser.parseString(result).asJsonObject.getAsJsonArray("items").iterator()

        var booksTotal = 9
        while (booksTotal >= 0) {
            val bookJsonElement = booksJsonElements.next()
            booksJsonElements.remove()

            val title =
                bookJsonElement.asJsonObject.get("volumeInfo")?.asJsonObject?.get("title")?.asString
                    ?: continue
            val author =
                bookJsonElement.asJsonObject.get("volumeInfo")?.asJsonObject?.get("authors")?.asJsonArray?.get(0)?.asString
                    ?: continue
            val releaseDate =
                bookJsonElement.asJsonObject.get("volumeInfo")?.asJsonObject?.get("publishedDate")?.asString
                    ?: continue
            val publisher =
                bookJsonElement.asJsonObject.get("volumeInfo")?.asJsonObject?.get("publisher")?.asString
                    ?: continue
            val description =
                bookJsonElement.asJsonObject.get("volumeInfo")?.asJsonObject?.get("description")?.asString
                    ?: continue
            val cover =
                bookJsonElement.asJsonObject.get("volumeInfo")?.asJsonObject?.get("imageLinks")?.asJsonObject?.get("thumbnail")?.asString
                    ?: continue

            booksList.add(
                Book(
                    title,
                    author,
                    BitmapFactory.decodeStream(URL(cover).openConnection().getInputStream())
                )
            )

            booksTotal--

            /*booksList.add(
                Book(
                    bookJsonElement.asJsonObject.get("volumeInfo").asJsonObject.get("title").asString,
                    authorsList
                    bookJsonElement.getAsJsonObject().get("volumeInfo").getAsJsonObject().get("publishedDate").getAsString(),
                    bookJsonElement.getAsJsonObject().get("volumeInfo").getAsJsonObject().get("publisher").getAsString(),
                    bookJsonElement.getAsJsonObject().get("volumeInfo").getAsJsonObject().get("description").getAsString(),
                    bookJsonElement.getAsJsonObject().get("volumeInfo").getAsJsonObject().get("imageLinks").getAsString().getAsJsonObject().get("thumbnail").getAsString()
                )
            )*/
            val z = 0
        }
        
        return booksList
    }
}
