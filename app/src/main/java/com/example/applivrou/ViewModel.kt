package com.example.applivrou

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel: ViewModel() {
    private val bookshelfRepository = BookshelfRepository()

    var booksList = MutableLiveData<ArrayList<Book>>()// .apply { value = booksShelf }

    fun updateBooksList(category: String) {
        Thread(Runnable {
            booksList.postValue(bookshelfRepository.getSamplesOfCategory(category))
        }).start()
    }
}