package com.vr.app.sh.di

import android.content.Context
import com.vr.app.sh.data.api.NetworkService
import com.vr.app.sh.data.repository.*
import com.vr.app.sh.domain.UseCase.GetAllBookListInternet
import com.vr.app.sh.domain.UseCase.InternetConnection
import com.vr.app.sh.domain.UseCase.SaveBookListInBD
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideInternetRepoImpl(): InternetRepoImpl {
        return InternetRepoImpl(NetworkService.getInstance())
    }

    @Provides
    fun provideUserRepoImpl(context: Context): UserRepoImpl {
        return UserRepoImpl(context = context)
    }

    @Provides
    fun provideBookRepoImpl(context: Context):BookRepoImpl{
        return BookRepoImpl(context = context)
    }

    @Provides
    fun provideTestsRepoImpl(context: Context): TestsRepoImpl {
        return TestsRepoImpl(context = context)
    }

    @Provides
    fun provideQuestionsRepoImpl(context: Context): QuestionsRepoImpl {
        return QuestionsRepoImpl(context = context)
    }

}