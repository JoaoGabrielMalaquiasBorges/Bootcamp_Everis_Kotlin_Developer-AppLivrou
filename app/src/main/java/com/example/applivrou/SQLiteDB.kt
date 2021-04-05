package com.example.applivrou

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL

class SQLiteDB(context: Context) : SQLiteOpenHelper(context, "SQLiteDB", null, 1), BookshelfDAO {
    private val booksList: ArrayList<Book> = ArrayList()

    private val TABLE_NAME = "BOOKS"

    private val CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            ID INTEGER NOT NULL,
            TITLE VARCHAR NOT NULL,
            AUTHOR VARCHAR NOT NULL,
            RELEASE_DATE VARCHAR NOT NULL,
            PUBLISHER VARCHAR NOT NULL,
            DESCRIPTION TEXT NOT NULL,
            COVER VARCHAR NOT NULL,
            CATEGORY VARCHAR NOT NULL,
            
            PRIMARY KEY ${'('}ID${')'}
        )
    """

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun getBooks(category: String): ArrayList<Book> {
        booksList?.clear() ?: booksList
        
        val db = readableDatabase ?: return booksList
        val sql = "SELECT * FROM $TABLE_NAME"
        var cursor = db.rawQuery(sql, null) ?: return booksList

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
        
        db.close()

        return booksList
    }
    
    fun saveBooks(booksList: ArrayList<JsonObject>, category: String) {
        val db = writableDatabase ?: return
        var sql: String
        
        booksList.forEach { book ->
            sql = """
                INSERT INTO $TABLE_NAME (
                    ID,
                    TITLE,
                    AUTHOR,
                    RELEASE_DATE,
                    PUBLISHER,
                    DESCRIPTION,
                    COVER,
                    CATEGORY
                ) VALUES (
                    ${book.get("id").asString},
                    ${book.get("title").asString},
                    ${book.get("author").asString},
                    ${book.get("releaseDate").asString},
                    ${book.get("publisher").asString},
                    ${book.get("description").asString},
                    ${book.get("cover").asString},
                    $category
                )
            """
        
            db.rawQuery(sql, null) ?: return
            db.close()
        }
    }
}
