package com.vr.app.sh.data.storage.room

import android.content.Context
import androidx.room.*
import com.vr.app.sh.data.storage.model.*
import com.vr.app.sh.data.storage.model.chat.*
import com.vr.app.sh.data.storage.model.users.UserEntity
import com.vr.app.sh.data.storage.room.dao.*
import com.vr.app.sh.data.storage.room.dao.user.DAOUser

@Database(
    entities = [BookEntity::class, LessonEntity::class, QuestionEntity::class, TestEntity::class,UserEntity::class,
               UsersInChatEntity::class,MessageEntity::class,FavoriteUserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {

    abstract fun bookDAO(): DAOBook
    abstract fun testDAO(): DAOTest
    abstract fun questionsDAO(): DAOQuestions
    abstract fun lessonsDAO(): DAOLessons
    abstract fun userDAO(): DAOUser
    abstract fun usersInChatDAO(): DAOUsersInChat
    abstract fun messagesDAO(): DAOMessage
    abstract fun favoriteUserDAO(): DAOFavoriteUser

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