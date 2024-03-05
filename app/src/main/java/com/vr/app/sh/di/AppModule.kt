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
        sendBook: SendBook
    ):AddBookViewModelFactory{
        return AddBookViewModelFactory(context,getAllBookListInternet,saveBookListInBD,sendBook)
    }

    @Provides
    fun provideAddTestViewModelFactory(
        context: Context,
        getInfoTests: GetListTestsInternet,
        saveTestInBD: SaveTestsInBD,
        sendTest: SendTestInfo,
        sendQuestions: SendQuestions
    ):AddTestViewModelFactory{
        return AddTestViewModelFactory(context,getInfoTests,saveTestInBD, sendTest, sendQuestions)
    }

    @Provides
    fun provideAllSubjectsViewModelFactory(
        context: Context,
        getListTestsInternet: GetListTestsInternet,
        saveListTestsInBD: SaveTestsInBD
    ): AllSubjectsViewModelFactory {
        return AllSubjectsViewModelFactory(context,getListTestsInternet, saveListTestsInBD)
    }

    @Provides
    fun provideAuthorizationViewModelFactory(
        context: Context,
        connectToWebSocket: ConnectToWebSocket,
        cleanUser: CleanUser,
        downloadUserPhoto: DownloadUserPhoto,
        authorization: Authorization,
        saveUser: SaveUser
    ): AuthorizationViewModelFactory {
        return AuthorizationViewModelFactory(context,connectToWebSocket,cleanUser,downloadUserPhoto,authorization, saveUser)
    }

    @Provides
    fun provideCustomWorkerFactory(
        connectToWebSocket: ConnectToWebSocket
    ):CustomWorkerFactory{
        return CustomWorkerFactory(connectToWebSocket)
    }

    @Provides
    fun provideBooksViewModelFactory(
        context: Context,
        getListBookInClass: GetListBookInClass,
        getBookFile: GetBookFile
    ): BooksViewModelFactory {
        return BooksViewModelFactory(context,getListBookInClass, getBookFile)
    }

    @Provides
    fun provideMenuViewModelFactory(
        context: Context,
        getListBookInternet: GetAllBookListInternet,
        saveListBookInBD: SaveBookListInBD,
        downloadUserPhoto: DownloadUserPhoto,
        cleanUser: CleanUser,
        cleanCookie: CleanCookie
    ):MenuViewModelFactory{
        return MenuViewModelFactory(context,getListBookInternet, saveListBookInBD,downloadUserPhoto,cleanUser, cleanCookie)
    }

    @Provides
    fun provideRegViewModelFactory(
        context: Context,
        saveUser: SaveUser,
        registration: Registration
    ):RegViewModelFactory{
        return RegViewModelFactory(context,saveUser, registration)
    }

    @Provides
    fun provideResultViewModelFactory(
        context: Context,
        sendResult: SendResult
    ):ResultViewModelFactory{
        return ResultViewModelFactory(context,sendResult)
    }

    @Provides
    fun provideTestsOneClassViewModelFactory(
        context: Context,
        getListTestsInClass: GetListTestsInClass,
        getListQuestions: GetListQuestions,
        saveQuestionsInBD: SaveQuestionsInBD
    ):TestsOneClassViewModelFactory{
        return TestsOneClassViewModelFactory(context,getListTestsInClass, getListQuestions, saveQuestionsInBD)
    }

    @Provides
    fun provideOpenTestViewModelFactory(
        getListQuestionsBD: GetListQuestionsBD
    ):OpenTestViewModelFactory{
        return OpenTestViewModelFactory(getListQuestionsBD)
    }

    @Provides
    fun provideDayViewModelFactory(
        context: Context,
        getLessonsInDay: GetLessonsInDay
    ):DayViewModelFactory{
        return DayViewModelFactory(context,getLessonsInDay)
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

    @Provides
    fun provideAllUsersViewModelFactory(context: Context,getUsersAndSaveLocal: GetUsersAndSaveLocal):AllUsersViewModelFactory{
        return AllUsersViewModelFactory(context,getUsersAndSaveLocal)
    }

    @Provides
    fun provideChatWithUserViewModelFactory(
        context: Context,
        sendMessage: SendMessage,
        getMessagesInChat: GetMessagesInChat
    ):ChatViewModelFactory{
        return ChatViewModelFactory(context,sendMessage,getMessagesInChat)
    }

    @Provides
    fun provideMyChatsViewModelFactory(
        context: Context,
        getMyChats: GetMyChats
    ):MyChatsViewModelFactory{
        return MyChatsViewModelFactory(context,getMyChats)
    }

    @Provides
    fun provideSelectChatsViewModelFactory(
        context: Context,
        getFavoriteUsers: GetFavoriteUsers,
        getChatIdByUser: GetChatIdByUser
    ):SelectChatsViewModelFactory{
        return SelectChatsViewModelFactory(context,getFavoriteUsers,getChatIdByUser)
    }

    @Provides
    fun provideFavoriteUsersViewModelFactory(
        context: Context,
        getUsersToSelect: GetUsersToSelect,
        addFavoriteUser: AddFavoriteUser,
        deleteFavoriteUser: DeleteFavoriteUser
    ):FavoriteUsersViewModelFactory{
        return FavoriteUsersViewModelFactory(context,getUsersToSelect,addFavoriteUser,deleteFavoriteUser)
    }
}