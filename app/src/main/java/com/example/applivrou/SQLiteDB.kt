package com.example.applivrou

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL

class SQLiteDB(context: Context) : SQLiteOpenHelper(context, "NAME", null, 1), BookshelfDAO {
    companion object {
        private const val NAME = "SQLiteDB"
        private const val VERSION = 1
    }

    private var booksList = arrayListOf<Book>()

    private val TABLE_NAME = "BOOKS"

    private val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            ID INTEGER NOT NULL,
            TITLE VARCHAR NOT NULL,
            AUTHOR VARCHAR NOT NULL,
            EDITION VARCHAR NOT NULL,
            RELEASE_DATE VARCHAR NOT NULL,
            PUBLISHER VARCHAR NOT NULL,
            DESCRIPTION TEXT NOT NULL,
            COVER VARCHAR NOT NULL,
            CATEGORY VARCHAR NOT NULL,
            
            PRIMARY KEY ${'('}ID AUTOINCREMENT${')'}
        )
    """

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun getBooks(category: String): ArrayList<Book> {
        val db = readableDatabase ?: return arrayListOf()
        val sql = "SELECT * FROM $TABLE_NAME"
        var cursor = db.rawQuery(sql, null) ?: return arrayListOf()

        while (cursor.moveToNext()) {
            val url =
                    URL(cursor.getString(cursor.getColumnIndex("COVER")))
            val bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())

            booksList.add(
                Book(
                    cursor.getString(cursor.getColumnIndex("TITLE")),
                    cursor.getString(cursor.getColumnIndex("AUTHOR")),
                    bmp
                )
            )
        }

        return booksList
    }
}