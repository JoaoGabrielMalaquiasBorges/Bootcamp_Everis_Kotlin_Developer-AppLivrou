package com.example.applivrou.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applivrou.BookshelfRepository
import com.example.applivrou.model.Book

class ViewModel: ViewModel() {
    private val bookshelfRepository = BookshelfRepository()

    var booksList = MutableLiveData<ArrayList<Book>>()

    fun updateBooksList(category: String) {
        Thread(Runnable {
            booksList.postValue(bookshelfRepository.getSamplesOfCategory(category))
        }).start()
    }
}