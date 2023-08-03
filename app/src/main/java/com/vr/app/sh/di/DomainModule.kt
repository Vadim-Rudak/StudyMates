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
    fun provideSaveBookListInBD(daoBook: DAOBook):SaveBookListInBD{
        return SaveBookListInBD(daoBook)
    }

    @Provides
    fun provideGetListAllBook(internetRepoImpl: InternetRepoImpl):GetAllBookListInternet{
        return GetAllBookListInternet(internetRepoImpl)
    }

    @Provides
    fun provideSaveTestsInBD(daoTest: DAOTest):SaveTestsInBD{
        return SaveTestsInBD(daoTest)
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
    fun provideAuthSetUserInBD(daoUser: DAOUser):SetUserInBD{
        return SetUserInBD(daoUser = daoUser)
    }

    @Provides
    fun provideGetListBookInClass(daoBook: DAOBook):GetListBookInClass{
        return GetListBookInClass(daoBook)
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
    fun provideGetUserBD(daoUser: DAOUser):GetUserBD{
        return GetUserBD(daoUser = daoUser)
    }

    @Provides
    fun provideGetListTestsInClass(daoTest: DAOTest):GetListTestsInClass{
        return GetListTestsInClass(daoTest)
    }

    @Provides
    fun provideGetListQuestions(internetRepoImpl: InternetRepoImpl):GetListQuestions{
        return GetListQuestions(internetRepoImpl)
    }

    @Provides
    fun provideSaveQuestionsInBD(questionsDAO:DAOQuestions):SaveQuestionsInBD{
        return SaveQuestionsInBD(questionsDAO)
    }

    @Provides
    fun provideGetQuestionsFromBD(questionsDAO:DAOQuestions):GetListQuestionsBD{
        return GetListQuestionsBD(questionsRepo = questionsDAO)
    }

}