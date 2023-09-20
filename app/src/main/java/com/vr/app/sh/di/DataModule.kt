package com.vr.app.sh.di

import android.content.Context
import com.vr.app.sh.data.api.NetworkService
import com.vr.app.sh.data.repository.DAOBook
import com.vr.app.sh.data.repository.DAOLessons
import com.vr.app.sh.data.repository.DAOQuestions
import com.vr.app.sh.data.repository.DAOTest
import com.vr.app.sh.data.repository.InternetRepoImpl
import com.vr.app.sh.data.repository.RoomDB
import com.vr.app.sh.data.repository.UserPreferences
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideInternetRepoImpl(): InternetRepoImpl {
        return InternetRepoImpl(NetworkService.getInstance())
    }

    @Provides
    fun provideUserPreferences(context: Context): UserPreferences {
        return UserPreferences(context)
    }

    @Provides
    fun provideBookRepoImpl(context: Context):DAOBook{
        return RoomDB.getDatabase(context = context).bookDAO()
    }

    @Provides
    fun provideTestsRepoImpl(context: Context): DAOTest {
        return RoomDB.getDatabase(context = context).testDAO()
    }

    @Provides
    fun provideDAOQuestions(context: Context): DAOQuestions {
        return RoomDB.getDatabase(context = context).questionsDAO()
    }

    @Provides
    fun provideDAOLessons(context: Context): DAOLessons {
        return RoomDB.getDatabase(context = context).lessonsDAO()
    }

}