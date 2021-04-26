package com.example.applivrou.model

import android.app.Application

class Singleton : Application() {
    var helperSQLite: HelperSQLite? = null
        private set

    companion object {
        lateinit var instance: Singleton
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        helperSQLite = HelperSQLite(this)
    }
}