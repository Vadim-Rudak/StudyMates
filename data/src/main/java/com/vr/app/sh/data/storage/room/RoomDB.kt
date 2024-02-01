package com.vr.app.sh.data.storage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vr.app.sh.data.storage.model.BookEntity
import com.vr.app.sh.data.storage.model.LessonEntity
import com.vr.app.sh.data.storage.model.QuestionEntity
import com.vr.app.sh.data.storage.model.TestEntity
import com.vr.app.sh.data.storage.model.users.UserEntity
import com.vr.app.sh.data.storage.room.dao.DAOBook
import com.vr.app.sh.data.storage.room.dao.DAOLessons
import com.vr.app.sh.data.storage.room.dao.DAOQuestions
import com.vr.app.sh.data.storage.room.dao.DAOTest
import com.vr.app.sh.data.storage.room.dao.user.DAOUser

@Database(
    entities = [BookEntity::class, LessonEntity::class, QuestionEntity::class, TestEntity::class,UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {

    abstract fun bookDAO(): DAOBook
    abstract fun testDAO(): DAOTest
    abstract fun questionsDAO(): DAOQuestions
    abstract fun lessonsDAO(): DAOLessons
    abstract fun userDAO(): DAOUser

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