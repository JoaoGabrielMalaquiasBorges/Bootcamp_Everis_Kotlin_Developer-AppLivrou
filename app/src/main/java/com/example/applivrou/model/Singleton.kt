package com.example.applivrou.model

import android.app.Application

class Singleton : Application() {
    var bookshelfDB: SQLiteDB? = null
        private set

    companion object {
        lateinit var instance: Singleton
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        bookshelfDB = SQLiteDB(this)
    }
}