package com.vr.app.sh.di

import android.content.Context
import com.vr.app.sh.data.api.InternetRepoImpl
import com.vr.app.sh.data.api.NetworkService
import com.vr.app.sh.data.storage.room.RoomDB
import com.vr.app.sh.data.storage.room.repo.BookRepoImpl
import com.vr.app.sh.data.storage.room.repo.LessonsRepoImpl
import com.vr.app.sh.data.storage.room.repo.QuestionsRepoImpl
import com.vr.app.sh.data.storage.room.repo.TestRepoImpl
import com.vr.app.sh.data.storage.sharedprefs.CookiePreferences
import com.vr.app.sh.data.storage.sharedprefs.UserPreferences
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideInternetRepoImpl(context: Context): InternetRepoImpl {
        return InternetRepoImpl(context, NetworkService.getInstance(context))
    }

    @Provides
    fun provideCookiePreferences(context: Context): CookiePreferences {
        return CookiePreferences(context)
    }

    @Provides
    fun provideUserPreferences(context: Context): UserPreferences {
        return UserPreferences(context)
    }

    @Provides
    fun provideRoom(context: Context):RoomDB{
        return RoomDB.getDatabase(context = context)
    }

    @Provides
    fun provideBookRepoImpl(roomDB: RoomDB): BookRepoImpl {
        return BookRepoImpl(roomDB.bookDAO())
    }

    @Provides
    fun provideLessonsRepoImpl(roomDB: RoomDB):LessonsRepoImpl{
        return LessonsRepoImpl(roomDB.lessonsDAO())
    }

    @Provides
    fun provideQuestionsRepoImpl(roomDB: RoomDB):QuestionsRepoImpl{
        return QuestionsRepoImpl(roomDB.questionsDAO())
    }

    @Provides
    fun provideTestRepoImpl(roomDB: RoomDB):TestRepoImpl{
        return TestRepoImpl(roomDB.testDAO())
    }

}