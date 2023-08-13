package com.vr.app.sh.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vr.app.sh.data.model.Book
import com.vr.app.sh.data.model.Lesson
import com.vr.app.sh.data.model.Question
import com.vr.app.sh.data.model.Test
import com.vr.app.sh.data.model.User

@Database(entities = arrayOf(User::class,Book::class,Test::class,Question::class,Lesson::class), version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {

    abstract fun userDAO():DAOUser
    abstract fun bookDAO():DAOBook
    abstract fun testDAO():DAOTest
    abstract fun questionsDAO():DAOQuestions
    abstract fun lessonsDAO():DAOLessons

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabase(context: Context): RoomDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "SH.db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}