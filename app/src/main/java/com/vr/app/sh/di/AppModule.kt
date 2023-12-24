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
        context: Context,
        getAllBookListInternet: GetAllBookListInternet,
        saveBookListInBD: SaveBookListInBD,
        internetConnection: InternetConnection
    ):AddBookViewModelFactory{
        return AddBookViewModelFactory(context,getAllBookListInternet, saveBookListInBD, internetConnection)
    }

    @Provides
    fun provideAddTestViewModelFactory(
        context: Context,
        getInfoTests: GetListTestsInternet,
        saveTestInBD: SaveTestsInBD,
        sendTest: SendTestInfo,
        sendQuestions: SendQuestions,
        internetConnection: InternetConnection
    ):AddTestViewModelFactory{
        return AddTestViewModelFactory(context,getInfoTests,saveTestInBD, sendTest, sendQuestions, internetConnection)
    }

    @Provides
    fun provideAllSubjectsViewModelFactory(
        context: Context,
        getListTestsInternet: GetListTestsInternet,
        saveListTestsInBD: SaveTestsInBD,
        internetConnection: InternetConnection
    ): AllSubjectsViewModelFactory {
        return AllSubjectsViewModelFactory(context,getListTestsInternet, saveListTestsInBD, internetConnection)
    }

    @Provides
    fun provideAuthorizationViewModelFactory(
        context: Context,
        cleanUser: CleanUser,
        downloadUserPhoto: DownloadUserPhoto,
        authorization: Authorization,
        saveUser: SaveUser,
        internetConnection: InternetConnection
    ): AuthorizationViewModelFactory {
        return AuthorizationViewModelFactory(context,cleanUser,downloadUserPhoto,authorization, saveUser, internetConnection)
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
        context: Context,
        getListBookInternet: GetAllBookListInternet,
        saveListBookInBD: SaveBookListInBD,
        internetConnection: InternetConnection,
        downloadUserPhoto: DownloadUserPhoto,
        cleanUser: CleanUser,
        cleanCookie: CleanCookie
    ):MenuViewModelFactory{
        return MenuViewModelFactory(context,getListBookInternet, saveListBookInBD, internetConnection,downloadUserPhoto,cleanUser, cleanCookie)
    }

    @Provides
    fun provideRegViewModelFactory(
        context: Context,
        saveUser: SaveUser,
        registration: Registration,
        internetConnection: InternetConnection
    ):RegViewModelFactory{
        return RegViewModelFactory(context,saveUser, registration, internetConnection)
    }

    @Provides
    fun provideResultViewModelFactory(
        context: Context,
        sendResult: SendResult,
        internetConnection: InternetConnection
    ):ResultViewModelFactory{
        return ResultViewModelFactory(context,sendResult, internetConnection)
    }

    @Provides
    fun provideTestsOneClassViewModelFactory(
        context: Context,
        getListTestsInClass: GetListTestsInClass,
        getListQuestions: GetListQuestions,
        saveQuestionsInBD: SaveQuestionsInBD,
        internetConnection: InternetConnection,
    ):TestsOneClassViewModelFactory{
        return TestsOneClassViewModelFactory(context,getListTestsInClass, getListQuestions, saveQuestionsInBD, internetConnection)
    }

    @Provides
    fun provideOpenTestViewModelFactory(
        getListQuestionsBD: GetListQuestionsBD
    ):OpenTestViewModelFactory{
        return OpenTestViewModelFactory(getListQuestionsBD)
    }

    @Provides
    fun provideDayViewModelFactory(
        getLessonsInDay: GetLessonsInDay
    ):DayViewModelFactory{
        return DayViewModelFactory(getLessonsInDay)
    }

    @Provides
    fun provideTimeTableViewModelFactory(
        saveLessonInBD: SaveLessonInBD
    ):TimeTableViewModelFactory{
        return TimeTableViewModelFactory(saveLessonInBD)
    }

    @Provides
    fun provideMyProfileViewModelFactory(context: Context):MyProfileViewModelFactory{
        return MyProfileViewModelFactory(context)
    }

    @Provides
    fun provideVerificationViewModelFactory(
        context: Context,
        verificationUserInServer: VerificationUserInServer
    ):VerificationViewModelFactory{
        return VerificationViewModelFactory(context,verificationUserInServer)
    }
}