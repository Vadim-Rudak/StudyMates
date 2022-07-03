package com.vr.app.sh.data.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.Cursor.FIELD_TYPE_STRING
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.data.model.DBContract
import com.vr.app.sh.domain.repository.BookRepo

class BookRepoImpl (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), BookRepo{
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertBeer(book: Book): Boolean {

        val db = writableDatabase

        val values = ContentValues()
        values.put(DBContract.BookEntry.COLUMN_ID, book.id)
        values.put(DBContract.BookEntry.COLUMN_NUM_CLASS, book.numclass)
        values.put(DBContract.BookEntry.COLUMN_NAME_BOOK, book.namebook)
        values.put(DBContract.BookEntry.COLUMN_IMAGE_BOOK, book.imagebook)

        val newRowId = db.insert(DBContract.BookEntry.TABLE_NAME, null, values)

        return true
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "SH.db"

        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.BookEntry.TABLE_NAME + " (" +
                    DBContract.BookEntry.COLUMN_ID + " TEXT PRIMARY KEY," +
                    DBContract.BookEntry.COLUMN_NUM_CLASS + " TEXT," +
                    DBContract.BookEntry.COLUMN_NAME_BOOK + " TEXT," +
                    DBContract.BookEntry.COLUMN_IMAGE_BOOK + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.BookEntry.TABLE_NAME
    }

    override fun getBookInOneClass(num_class: Int): ArrayList<Book> {
        val beer = ArrayList<Book>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.BookEntry.TABLE_NAME + " WHERE " + DBContract.BookEntry.COLUMN_NUM_CLASS + "='" + num_class + "'", null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var id : Int
        var numclass : Int
        var namebook : String
        var imagebook : String

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                id = cursor.getInt(0)
                numclass = cursor.getInt(1)
                namebook = cursor.getString(2)
                if (cursor.getType(3) == FIELD_TYPE_STRING){
                    imagebook = cursor.getString(3)
                }else{
                    imagebook = ""
                }
                beer.add(Book(id, numclass, namebook, imagebook))
                cursor.moveToNext()
            }
        }
        return beer
    }

    override fun saveBooksInDB(listBook: List<Book>) {
        val db = writableDatabase
        onUpgrade(db,1,1)
        for (item in listBook){
            insertBeer(item)
        }
    }
}