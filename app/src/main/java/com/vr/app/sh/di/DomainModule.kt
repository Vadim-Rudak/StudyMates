package com.vr.app.sh.di

import com.vr.app.sh.data.api.BookInternetRepoImpl
import com.vr.app.sh.data.api.InternetRepoImpl
import com.vr.app.sh.data.api.PhotoInternetRepoImpl
import com.vr.app.sh.data.api.webSocket.WebSocketImpl
import com.vr.app.sh.data.storage.room.repo.*
import com.vr.app.sh.data.storage.sharedprefs.*
import com.vr.app.sh.domain.UseCase.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideConnectToWebSocket(webSocketImpl: WebSocketImpl):ConnectToWebSocket{
        return ConnectToWebSocket(webSocketImpl)
    }

    @Provides
    fun provideSaveBookListInBD(bookRepoImpl: BookRepoImpl):SaveBookListInBD{
        return SaveBookListInBD(bookRepoImpl)
    }

    @Provides
    fun provideGetListAllBook(bookInternetRepoImpl: BookInternetRepoImpl):GetAllBookListInternet{
        return GetAllBookListInternet(bookInternetRepoImpl)
    }

    @Provides
    fun provideSaveTestsInBD(testRepoImpl: TestRepoImpl):SaveTestsInBD{
        return SaveTestsInBD(testRepoImpl)
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
    fun provideSendBook(bookInternetRepoImpl: BookInternetRepoImpl):SendBook{
        return SendBook(bookInternetRepoImpl)
    }

    @Provides
    fun provideAuthorization(internetRepoImpl: InternetRepoImpl):Authorization{
        return Authorization(internetRepoImpl)
    }

    @Provides
    fun provideAuthSetUserInBD(userPreferences: UserPreferences):SaveUser{
        return SaveUser(userPreferences)
    }

    @Provides
    fun provideGetListBookInClass(bookRepoImpl: BookRepoImpl):GetListBookInClass{
        return GetListBookInClass(bookRepoImpl)
    }

    @Provides
    fun provideGetBookFile(bookInternetRepoImpl: BookInternetRepoImpl):GetBookFile{
        return GetBookFile(bookInternetRepoImpl)
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
    fun provideGetListTestsInClass(testRepoImpl: TestRepoImpl):GetListTestsInClass{
        return GetListTestsInClass(testRepoImpl)
    }

    @Provides
    fun provideGetListQuestions(internetRepoImpl: InternetRepoImpl):GetListQuestions{
        return GetListQuestions(internetRepoImpl)
    }

    @Provides
    fun provideSaveQuestionsInBD(questionsRepoImpl: QuestionsRepoImpl):SaveQuestionsInBD{
        return SaveQuestionsInBD(questionsRepoImpl)
    }

    @Provides
    fun provideGetQuestionsFromBD(questionsRepoImpl: QuestionsRepoImpl):GetListQuestionsBD{
        return GetListQuestionsBD(questionsRepoImpl)
    }

    @Provides
    fun provideGetLessonsInDay(lessonsRepoImpl: LessonsRepoImpl):GetLessonsInDay{
        return GetLessonsInDay(lessonsRepoImpl)
    }

    @Provides
    fun provideSaveLessonInBD(lessonsRepoImpl: LessonsRepoImpl):SaveLessonInBD{
        return SaveLessonInBD(lessonsRepoImpl)
    }

    @Provides
    fun provideDownloadPhoto(photoInternetRepoImpl: PhotoInternetRepoImpl):DownloadUserPhoto{
        return DownloadUserPhoto(photoInternetRepoImpl)
    }

    @Provides
    fun provideClearUser(userPreferences: UserPreferences):CleanUser{
        return CleanUser(userPreferences)
    }

    @Provides
    fun provideClearCookie(cookiePreferences: CookiePreferences):CleanCookie{
        return CleanCookie(cookiePreferences)
    }

    @Provides
    fun provideVerificationUser(photoInternetRepoImpl: PhotoInternetRepoImpl):VerificationUserInServer{
        return VerificationUserInServer(photoInternetRepoImpl)
    }
}