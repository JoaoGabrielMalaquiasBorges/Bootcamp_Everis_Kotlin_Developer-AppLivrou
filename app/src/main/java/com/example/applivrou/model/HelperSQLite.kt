package com.example.applivrou.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.google.gson.JsonObject
import java.util.*

class HelperSQLite(context: Context) : SQLiteOpenHelper(context, "SQLiteDB", null, 1), BooksDAO {
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
        booksList.clear()
        
        val db = readableDatabase ?: return booksList
        val sql = "SELECT * FROM BOOKS WHERE CATEGORY = ?"
        val cursor = db.rawQuery(sql, arrayOf(category)) ?: return booksList

        while (cursor.moveToNext()) {
            booksList.add(
                Book(
                    cursor.getString(cursor.getColumnIndex("TITLE")),
                    cursor.getString(cursor.getColumnIndex("AUTHOR")),
                    cursor.getString(cursor.getColumnIndex("DESCRIPTION")),
                    cursor.getString(cursor.getColumnIndex("COVER"))
                )
            )
        }

        db.close()

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
            ))
        }

        db.close()
    }
}
