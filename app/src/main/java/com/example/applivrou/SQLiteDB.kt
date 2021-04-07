package com.example.applivrou

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.gson.JsonObject
import java.net.URL
import java.util.*

class SQLiteDB(context: Context) : SQLiteOpenHelper(context, "SQLiteDB", null, 1), BookshelfDAO {
    private val booksList: ArrayList<Book> = ArrayList()

    private val CREATE_TABLE = """
        CREATE TABLE BOOKS (
            BOOK_ID TEXT NOT NULL,
            TITLE TEXT NOT NULL,
            AUTHOR TEXT NOT NULL,
            RELEASE_DATE TEXT NOT NULL,
            PUBLISHER TEXT NOT NULL,
            DESCRIPTION TEXT NOT NULL,
            COVER TEXT NOT NULL,
            CATEGORY TEXT NOT NULL,
            
            PRIMARY KEY ${'('}BOOK_ID${')'}
        )
    """

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun getBooks(category: String): ArrayList<Book> {
        booksList?.clear()
        
        val db = readableDatabase ?: return booksList
        val sql = "SELECT * FROM BOOKS WHERE CATEGORY = ?"
        var cursor = db.rawQuery(sql, arrayOf(category)) ?: return booksList

        var url: URL
        var bmp: Bitmap

        while (cursor.moveToNext()) {
            url = URL(cursor.getString(cursor.getColumnIndex("COVER")))
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

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
    
    fun saveBooks(booksList: ArrayList<JsonObject>, category: String) {
        val db = writableDatabase ?: return

        val sql = """
            INSERT INTO BOOKS (
                BOOK_ID,
                TITLE,
                AUTHOR,
                RELEASE_DATE,
                PUBLISHER,
                DESCRIPTION,
                COVER,
                CATEGORY
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """

        booksList.forEach { book ->
            db.execSQL(sql, arrayOf(
                book.get("id").asString,
                book.get("title").asString,
                book.get("author").asString,
                book.get("releaseDate").asString,
                book.get("publisher").asString,
                book.get("description").asString,
                book.get("cover").asString,
                category
            )) ?: return
        }
    }
}
