package com.vr.app.sh.di

import com.vr.app.sh.data.api.*
import com.vr.app.sh.data.api.webSocket.WebSocketImpl
import com.vr.app.sh.data.storage.room.repo.*
import com.vr.app.sh.data.storage.sharedprefs.*
import com.vr.app.sh.domain.UseCase.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideConnectToWebSocket(webSocketImpl: WebSocketImpl) = ConnectToWebSocket(webSocketImpl)
    @Provides
    fun provideSaveBookListInBD(bookRepoImpl: BookRepoImpl) = SaveBookListInBD(bookRepoImpl)
    @Provides
    fun provideGetListAllBook(bookInternetRepoImpl: BookInternetRepoImpl) = GetAllBookListInternet(bookInternetRepoImpl)
    @Provides
    fun provideSaveTestsInBD(testRepoImpl: TestRepoImpl) = SaveTestsInBD(testRepoImpl)
    @Provides
    fun provideGetInfoTests(internetRepoImpl: InternetRepoImpl) = GetListTestsInternet(internetRepoImpl)
    @Provides
    fun provideSendTest(internetRepoImpl: InternetRepoImpl) = SendTestInfo(internetRepoImpl)
    @Provides
    fun provideSendQuestions(internetRepoImpl: InternetRepoImpl) = SendQuestions(internetRepoImpl)
    @Provides
    fun provideSendBook(bookInternetRepoImpl: BookInternetRepoImpl) = SendBook(bookInternetRepoImpl)
    @Provides
    fun provideAuthorization(internetRepoImpl: InternetRepoImpl) = Authorization(internetRepoImpl)
    @Provides
    fun provideAuthSetUserInBD(userPreferences: UserPreferences) = SaveUser(userPreferences)
    @Provides
    fun provideGetListBookInClass(bookRepoImpl: BookRepoImpl) = GetListBookInClass(bookRepoImpl)
    @Provides
    fun provideGetBookFile(bookInternetRepoImpl: BookInternetRepoImpl) = GetBookFile(bookInternetRepoImpl)
    @Provides
    fun provideRegistration(internetRepoImpl: InternetRepoImpl) = Registration(internetRepoImpl)
    @Provides
    fun provideSendResult(internetRepoImpl: InternetRepoImpl) = SendResult(internetRepoImpl)
    @Provides
    fun provideGetListTestsInClass(testRepoImpl: TestRepoImpl) = GetListTestsInClass(testRepoImpl)
    @Provides
    fun provideGetListQuestions(internetRepoImpl: InternetRepoImpl) = GetListQuestions(internetRepoImpl)
    @Provides
    fun provideSaveQuestionsInBD(questionsRepoImpl: QuestionsRepoImpl) = SaveQuestionsInBD(questionsRepoImpl)
    @Provides
    fun provideGetQuestionsFromBD(questionsRepoImpl: QuestionsRepoImpl) = GetListQuestionsBD(questionsRepoImpl)
    @Provides
    fun provideGetLessonsInDay(lessonsRepoImpl: LessonsRepoImpl) = GetLessonsInDay(lessonsRepoImpl)
    @Provides
    fun provideSaveLessonInBD(lessonsRepoImpl: LessonsRepoImpl) = SaveLessonInBD(lessonsRepoImpl)
    @Provides
    fun provideDownloadPhoto(photoInternetRepoImpl: PhotoInternetRepoImpl) = DownloadUserPhoto(photoInternetRepoImpl)
    @Provides
    fun provideClearUser(userPreferences: UserPreferences) = CleanUser(userPreferences)
    @Provides
    fun provideClearCookie(cookiePreferences: CookiePreferences) = CleanCookie(cookiePreferences)
    @Provides
    fun provideVerificationUser(photoInternetRepoImpl: PhotoInternetRepoImpl) = VerificationUserInServer(photoInternetRepoImpl)
    @Provides
    fun provideGetUsersAndSaveLocal(userRepoImpl: UserRepoImpl,userInternetRepoImpl: UserInternetRepoImpl) = GetUsersAndSaveLocal(userRepoImpl,userInternetRepoImpl)
    @Provides
    fun provideSendMessage(chatInternetRepoImpl: ChatInternetRepoImpl) = SendMessage(chatInternetRepoImpl)
    @Provides
    fun provideGetMyChats(usersInChatRepoImpl: UsersInChatRepoImpl) = GetMyChats(usersInChatRepoImpl)
    @Provides
    fun provideGetMessagesInChat(messagesRepoImpl: MessagesRepoImpl) = GetMessagesInChat(messagesRepoImpl)
    @Provides
    fun provideAddFavoriteUsers(favoriteRepoImpl: FavoriteRepoImpl) = AddFavoriteUser(favoriteRepoImpl)
    @Provides
    fun provideGetFavoriteUsers(favoriteRepoImpl: FavoriteRepoImpl) = GetFavoriteUsers(favoriteRepoImpl)
    @Provides
    fun provideGetUsersToSelect(favoriteRepoImpl: FavoriteRepoImpl) = GetUsersToSelect(favoriteRepoImpl)
    @Provides
    fun provideDeleteFavoriteUser(favoriteRepoImpl: FavoriteRepoImpl) = DeleteFavoriteUser(favoriteRepoImpl)
    @Provides
    fun provideGetChatIdByUser(usersInChatRepoImpl: UsersInChatRepoImpl) = GetChatIdByUser(usersInChatRepoImpl)
}