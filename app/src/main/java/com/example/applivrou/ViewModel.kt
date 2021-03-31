package com.example.applivrou

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL

class ViewModel: ViewModel() {
    private var booksShelf = arrayListOf<Book>()

    var booksList = MutableLiveData<ArrayList<Book>>()// .apply { value = booksShelf }

    fun updateBooksList() {
        Thread(Runnable {
            val url =
                URL("http://books.google.com/books/content?id=Wv6toQEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api")
            val bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())

            booksShelf.add(
                Book(
                    "The Concepts of Programming Languages",
                    "Robert W. Sebesta",
                    bmp
                )
            )

            booksList.postValue(booksShelf)
        }).start()
    }
}