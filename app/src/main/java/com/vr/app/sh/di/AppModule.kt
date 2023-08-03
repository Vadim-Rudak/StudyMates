package com.vr.app.sh.di

import android.content.Context
import com.vr.app.sh.domain.UseCase.*
import com.vr.app.sh.ui.base.*
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext():Context{
        return context
    }

    @Provides
    fun provideAddBookViewModelFactory(
        getAllBookListInternet: GetAllBookListInternet,
        saveBookListInBD: SaveBookListInBD,
        internetConnection: InternetConnection
    ):AddBookViewModelFactory{
        return AddBookViewModelFactory(getAllBookListInternet, saveBookListInBD, internetConnection)
    }

    @Provides
    fun provideAddTestViewModelFactory(
        getInfoTests: GetListTestsInternet,
        saveTestInBD: SaveTestsInBD,
        sendTest: SendTestInfo,
        sendQuestions: SendQuestions,
        internetConnection: InternetConnection
    ):AddTestViewModelFactory{
        return AddTestViewModelFactory(getInfoTests,saveTestInBD, sendTest, sendQuestions, internetConnection)
    }

    @Provides
    fun provideAllSubjectsViewModelFactory(
        getListTestsInternet: GetListTestsInternet,
        saveListTestsInBD: SaveTestsInBD,
        internetConnection: InternetConnection
    ): AllSubjectsViewModelFactory {
        return AllSubjectsViewModelFactory(getListTestsInternet, saveListTestsInBD, internetConnection)
    }

    @Provides
    fun provideAuthorizationViewModelFactory(
        authorization: Authorization,
        setUserInBD: SetUserInBD,
        internetConnection: InternetConnection
    ): AuthorizationViewModelFactory {
        return AuthorizationViewModelFactory(authorization, setUserInBD, internetConnection)
    }

    @Provides
    fun provideBooksViewModelFactory(
        context: Context,
        getListBookInClass: GetListBookInClass,
        getBookFile: GetBookFile,
        internetConnection: InternetConnection
    ): BooksViewModelFactory {
        return BooksViewModelFactory(context,getListBookInClass, getBookFile, internetConnection)
    }

    @Provides
    fun provideMenuViewModelFactory(
        getListBookInternet: GetAllBookListInternet,
        saveListBookInBD: SaveBookListInBD,
        internetConnection: InternetConnection
    ):MenuViewModelFactory{
        return MenuViewModelFactory(getListBookInternet, saveListBookInBD, internetConnection)
    }

    @Provides
    fun provideRegViewModelFactory(
        registration: Registration,
        internetConnection: InternetConnection
    ):RegViewModelFactory{
        return RegViewModelFactory(registration, internetConnection)
    }

    @Provides
    fun provideResultViewModelFactory(
        sendResult: SendResult,
        getUser: GetUserBD,
        internetConnection: InternetConnection
    ):ResultViewModelFactory{
        return ResultViewModelFactory(sendResult, getUser, internetConnection)
    }

    @Provides
    fun provideTestsOneClassViewModelFactory(
        getListTestsInClass: GetListTestsInClass,
        getListQuestions: GetListQuestions,
        saveQuestionsInBD: SaveQuestionsInBD,
        internetConnection: InternetConnection,
    ):TestsOneClassViewModelFactory{
        return TestsOneClassViewModelFactory(getListTestsInClass, getListQuestions, saveQuestionsInBD, internetConnection)
    }

    @Provides
    fun provideOpenTestViewModelFactory(
        getListQuestionsBD: GetListQuestionsBD
    ):OpenTestViewModelFactory{
        return OpenTestViewModelFactory(getListQuestionsBD)
    }
}