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
import com.vr.app.sh.domain.model.User
import com.vr.app.sh.domain.repository.QuestionsRepo
import com.vr.app.sh.domain.repository.UserRepo

class UserRepoImpl(context:Context): SQLiteOpenHelper(context, TestsRepoImpl.DATABASE_NAME, null, TestsRepoImpl.DATABASE_VERSION),
    UserRepo {
    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("FFF", "Создание базы")
        db?.execSQL(SQL_USER_ENTRIE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(SQL_DELETE_USER)
        onCreate(db)
    }

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "SH.db"

        private val SQL_USER_ENTRIE =
            "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME + " (" +
                    DBContract.UserEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBContract.UserEntry.COLUMN_USER_NAME + " TEXT," +
                    DBContract.UserEntry.COLUMN_USER_ROLE + " TEXT)"

        private val SQL_DELETE_USER = "DROP TABLE IF EXISTS " + DBContract.UserEntry.TABLE_NAME
    }

    @Throws(SQLiteConstraintException::class)
    fun insertUser(user:User): Boolean {
        val db = writableDatabase

        val values = ContentValues()
        values.put(DBContract.UserEntry.COLUMN_USER_NAME, user.name_user)
        values.put(DBContract.UserEntry.COLUMN_USER_ROLE, user.role)

        val newRowId = db.insert(DBContract.UserEntry.TABLE_NAME, null, values)

        return true
    }

    override fun getUser(): User {
        val user = User()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_USER_ENTRIE)
            return user
        }

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                user.name_user = cursor.getString(1)
                user.role = cursor.getString(2)
                cursor.moveToNext()
            }
        }
        return user
    }

    override fun saveUserInDB(user: User) {
        val db = writableDatabase
        onUpgrade(db,1,1)
        insertUser(user = user)
    }
}