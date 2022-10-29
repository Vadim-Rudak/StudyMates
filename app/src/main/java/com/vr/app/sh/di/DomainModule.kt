package com.vr.app.sh.di

import android.content.Context
import com.vr.app.sh.data.repository.*
import com.vr.app.sh.domain.UseCase.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideInternetConnection(context: Context):InternetConnection{
        return InternetConnection(context = context)
    }

    @Provides
    fun provideSaveBookListInBD(bookRepoImpl:BookRepoImpl):SaveBookListInBD{
        return SaveBookListInBD(bookRepoImpl)
    }

    @Provides
    fun provideGetListAllBook(internetRepoImpl: InternetRepoImpl):GetAllBookListInternet{
        return GetAllBookListInternet(internetRepoImpl)
    }

    @Provides
    fun provideSaveTestsInBD(testsRepoImpl: TestsRepoImpl):SaveTestsInBD{
        return SaveTestsInBD(testsRepoImpl)
    }

    @Provides
    fun provideGetInfoTests(internetRepoImpl: InternetRepoImpl):GetListTestsInternet{
        return GetListTestsInternet(internetRepoImpl)
    }

    @Provides
    fun provideSendTest(internetRepoImpl: InternetRepoImpl):SendTestInfo{
        return SendTestInfo(internetRepoImpl)
    }

    @Provides
    fun provideSendQuestions(internetRepoImpl: InternetRepoImpl):SendQuestions{
        return SendQuestions(internetRepoImpl)
    }

    @Provides
    fun provideAuthorization(internetRepoImpl: InternetRepoImpl):Authorization{
        return Authorization(internetRepoImpl)
    }

    @Provides
    fun provideAuthSetUserInBD(userRepoImpl: UserRepoImpl):SetUserInBD{
        return SetUserInBD(userRepoImpl)
    }

    @Provides
    fun provideGetListBookInClass(bookRepoImpl:BookRepoImpl):GetListBookInClass{
        return GetListBookInClass(bookRepoImpl)
    }

    @Provides
    fun provideGetBookFile(internetRepoImpl: InternetRepoImpl):GetBookFile{
        return GetBookFile(internetRepoImpl)
    }

    @Provides
    fun provideRegistration(internetRepoImpl: InternetRepoImpl):Registration{
        return Registration(internetRepoImpl)
    }

    @Provides
    fun provideSendResult(internetRepoImpl: InternetRepoImpl):SendResult{
        return SendResult(internetRepoImpl)
    }

    @Provides
    fun provideGetUserBD(userRepoImpl: UserRepoImpl):GetUserBD{
        return GetUserBD(userRepoImpl)
    }

    @Provides
    fun provideGetListTestsInClass(testsRepoImpl: TestsRepoImpl):GetListTestsInClass{
        return GetListTestsInClass(testsRepoImpl)
    }

    @Provides
    fun provideGetListQuestions(internetRepoImpl: InternetRepoImpl):GetListQuestions{
        return GetListQuestions(internetRepoImpl)
    }

    @Provides
    fun provideSaveQuestionsInBD(questionsRepoImpl:QuestionsRepoImpl):SaveQuestionsInBD{
        return SaveQuestionsInBD(questionsRepoImpl)
    }

}