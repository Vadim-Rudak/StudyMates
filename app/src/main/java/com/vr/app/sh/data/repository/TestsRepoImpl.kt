package com.vr.app.sh.data.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.vr.app.sh.data.model.DBContract
import com.vr.app.sh.domain.model.Test
import com.vr.app.sh.domain.repository.TestsRepo

class TestsRepoImpl(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), TestsRepo {
    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("FFF", "Создание базы")
        db?.execSQL(SQL_TESTS_ENTRIE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(SQL_DELETE_TESTS)
        onCreate(db)
    }

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "SH.db"

        private val SQL_TESTS_ENTRIE =
            "CREATE TABLE " + DBContract.TestEntry.TABLE_NAME + " (" +
                    DBContract.TestEntry.COLUMN_ID + " TEXT PRIMARY KEY," +
                    DBContract.TestEntry.COLUMN_SUBJECT + " TEXT," +
                    DBContract.TestEntry.COLUMN_NUM_CLASS + " TEXT," +
                    DBContract.TestEntry.COLUMN_NAME_TEST + " TEXT," +
                    DBContract.TestEntry.COLUMN_NUM_QUESTIONS + " TEXT)"

        private val SQL_DELETE_TESTS = "DROP TABLE IF EXISTS " + DBContract.TestEntry.TABLE_NAME
    }

    @Throws(SQLiteConstraintException::class)
    fun insertTest(test: Test): Boolean {
        val db = writableDatabase

        val values = ContentValues()
        values.put(DBContract.TestEntry.COLUMN_ID, test.id)
        values.put(DBContract.TestEntry.COLUMN_SUBJECT, test.subject)
        values.put(DBContract.TestEntry.COLUMN_NUM_CLASS, test.numclass)
        values.put(DBContract.TestEntry.COLUMN_NAME_TEST, test.nametest)
        values.put(DBContract.TestEntry.COLUMN_NUM_QUESTIONS, test.numquestions)

        val newRowId = db.insert(DBContract.TestEntry.TABLE_NAME, null, values)

        return true
    }

    override fun getTestsInOneClass(num_class: Int): ArrayList<Test> {
        val listTests = ArrayList<Test>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.TestEntry.TABLE_NAME + " WHERE " + DBContract.TestEntry.COLUMN_NUM_CLASS + "='" + num_class + "'", null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_TESTS_ENTRIE)
            return ArrayList()
        }

        var id : Int
        var subject : String
        var numclass : Int
        var nametest : String
        var numquestions : Int

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                id = cursor.getInt(0)
                subject = cursor.getString(1)
                numclass = cursor.getInt(2)
                nametest = cursor.getString(3)
                numquestions = cursor.getInt(4)

                listTests.add(Test(id, subject, numclass, nametest,numquestions))
                cursor.moveToNext()
            }
        }
        return listTests
    }

    override fun saveTestsInDB(listTests: List<Test>) {
        val db = writableDatabase
        onUpgrade(db,1,1)
        for (item in listTests){
            insertTest(item)
        }
    }


}