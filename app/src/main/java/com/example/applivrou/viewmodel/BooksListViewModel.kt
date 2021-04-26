package com.example.applivrou.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applivrou.BooksListRepository
import com.example.applivrou.model.Book

class BooksListViewModel: ViewModel() {
    private val booksListRepository = BooksListRepository()

    var booksList = MutableLiveData<ArrayList<Book>>()

    fun updateBooksList(category: String) {
        Thread(Runnable {
            booksList.postValue(booksListRepository.getSamplesOfCategory(category))
        }).start()
    }
}