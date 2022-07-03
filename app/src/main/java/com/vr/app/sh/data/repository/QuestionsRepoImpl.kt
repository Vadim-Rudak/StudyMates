package com.vr.app.sh.data.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.vr.app.sh.data.model.DBContract
import com.vr.app.sh.domain.model.Question
import com.vr.app.sh.domain.repository.QuestionsRepo

class QuestionsRepoImpl (context: Context) : SQLiteOpenHelper(context, TestsRepoImpl.DATABASE_NAME, null, TestsRepoImpl.DATABASE_VERSION), QuestionsRepo {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_QUESTIONS_ENTRIE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(SQL_DELETE_QUESTIONS)
        onCreate(db)
    }

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "SH.db"

        private val SQL_QUESTIONS_ENTRIE =
            "CREATE TABLE " + DBContract.QuestionEntry.TABLE_NAME + " (" +
                    DBContract.QuestionEntry.COLUMN_ID + " TEXT PRIMARY KEY," +
                    DBContract.QuestionEntry.COLUMN_TEST_ID + " TEXT," +
                    DBContract.QuestionEntry.COLUMN_QUESTION + " TEXT," +
                    DBContract.QuestionEntry.COLUMN_OTV_1 + " TEXT," +
                    DBContract.QuestionEntry.COLUMN_OTV_2 + " TEXT," +
                    DBContract.QuestionEntry.COLUMN_OTV_3 + " TEXT," +
                    DBContract.QuestionEntry.COLUMN_OTV_4 + " TEXT," +
                    DBContract.QuestionEntry.COLUMN_CORRECT_OTV + " TEXT)"

        private val SQL_DELETE_QUESTIONS = "DROP TABLE IF EXISTS " + DBContract.QuestionEntry.TABLE_NAME
    }

    @Throws(SQLiteConstraintException::class)
    fun insertQuestion(question: Question): Boolean {
        val db = writableDatabase

        val values = ContentValues()
        values.put(DBContract.QuestionEntry.COLUMN_ID, question.id)
        values.put(DBContract.QuestionEntry.COLUMN_TEST_ID, question.testid)
        values.put(DBContract.QuestionEntry.COLUMN_QUESTION, question.question)
        values.put(DBContract.QuestionEntry.COLUMN_OTV_1, question.otv1)
        values.put(DBContract.QuestionEntry.COLUMN_OTV_2, question.otv2)
        values.put(DBContract.QuestionEntry.COLUMN_OTV_3, question.otv3)
        values.put(DBContract.QuestionEntry.COLUMN_OTV_4, question.otv4)
        values.put(DBContract.QuestionEntry.COLUMN_CORRECT_OTV, question.correctOtv)

        val newRowId = db.insert(DBContract.QuestionEntry.TABLE_NAME, null, values)

        return true
    }

    override fun getQuestions(): ArrayList<Question> {
        val listQuestions = ArrayList<Question>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.QuestionEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_QUESTIONS_ENTRIE)
            return ArrayList()
        }

        var id: Int
        var testid: Int
        var question: String?
        var otv1: String?
        var otv2: String?
        var otv3: String?
        var otv4: String?
        var correctOtv: String?

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                id = cursor.getInt(0)
                testid = cursor.getInt(1)
                question = cursor.getString(2)
                otv1 = cursor.getString(3)
                otv2 = cursor.getString(4)
                otv3 = cursor.getString(5)
                otv4 = cursor.getString(6)
                correctOtv = cursor.getString(7)

                listQuestions.add(Question(id = id, testid = testid , question = question, otv1 = otv1,
                    otv2 = otv2, otv3 = otv3, otv4 = otv4, correctOtv = correctOtv))
                cursor.moveToNext()
            }
        }
        return listQuestions
    }

    override fun saveQuestionsInDB(listQuestions: List<Question>) {
        val db = writableDatabase
        onUpgrade(db,1,1)
        for (item in listQuestions){
            insertQuestion(item)
        }
    }
}