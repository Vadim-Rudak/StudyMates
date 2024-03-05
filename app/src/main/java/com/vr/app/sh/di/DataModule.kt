package com.vr.app.sh.di

import android.content.Context
import com.vr.app.sh.data.api.BookInternetRepoImpl
import com.vr.app.sh.data.api.ChatInternetRepoImpl
import com.vr.app.sh.data.api.InternetRepoImpl
import com.vr.app.sh.data.api.NetworkService
import com.vr.app.sh.data.api.PhotoInternetRepoImpl
import com.vr.app.sh.data.api.UserInternetRepoImpl
import com.vr.app.sh.data.api.webSocket.ClientWebSocket
import com.vr.app.sh.data.api.webSocket.WebSocketImpl
import com.vr.app.sh.data.storage.room.RoomDB
import com.vr.app.sh.data.storage.room.repo.BookRepoImpl
import com.vr.app.sh.data.storage.room.repo.FavoriteRepoImpl
import com.vr.app.sh.data.storage.room.repo.LessonsRepoImpl
import com.vr.app.sh.data.storage.room.repo.MessagesRepoImpl
import com.vr.app.sh.data.storage.room.repo.QuestionsRepoImpl
import com.vr.app.sh.data.storage.room.repo.TestRepoImpl
import com.vr.app.sh.data.storage.room.repo.UserRepoImpl
import com.vr.app.sh.data.storage.room.repo.UsersInChatRepoImpl
import com.vr.app.sh.data.storage.sharedprefs.CookiePreferences
import com.vr.app.sh.data.storage.sharedprefs.UserPreferences
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideNetworkService(context: Context):NetworkService{
        return NetworkService.getInstance(context)
    }

    @Provides
    fun provideInternetRepoImpl(context: Context, networkService: NetworkService): InternetRepoImpl {
        return InternetRepoImpl(context, networkService)
    }

    @Provides
    fun providePhotoInternetRepoImpl(context: Context, networkService: NetworkService): PhotoInternetRepoImpl {
        return PhotoInternetRepoImpl(context, networkService)
    }

    @Provides
    fun provideBookInternetRepoImpl(context: Context, networkService: NetworkService): BookInternetRepoImpl {
        return BookInternetRepoImpl(context, networkService)
    }

    @Provides
    fun provideUserInternetRepoImpl(context: Context, networkService: NetworkService): UserInternetRepoImpl {
        return UserInternetRepoImpl(context, networkService)
    }

    @Provides
    fun provideChatInternetRepoImpl(context: Context, networkService: NetworkService): ChatInternetRepoImpl {
        return ChatInternetRepoImpl(context, networkService)
    }

    @Provides
    fun provideWebSocketImpl(clientWebSocket:ClientWebSocket):WebSocketImpl{
        return WebSocketImpl(clientWebSocket)
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

    @Provides
    fun provideUserRepoImpl(roomDB: RoomDB): UserRepoImpl {
        return UserRepoImpl(roomDB.userDAO())
    }

    @Provides
    fun provideUsersInChatRepoImpl(roomDB: RoomDB): UsersInChatRepoImpl {
        return UsersInChatRepoImpl(roomDB.usersInChatDAO())
    }

    @Provides
    fun provideMessagesRepoImpl(roomDB: RoomDB): MessagesRepoImpl {
        return MessagesRepoImpl(roomDB.messagesDAO())
    }

    @Provides
    fun provideFavoriteRepoImpl(roomDB: RoomDB): FavoriteRepoImpl {
        return FavoriteRepoImpl(roomDB.favoriteUserDAO())
    }

    @Provides
    fun provideClientWebSocket(context: Context,networkService: NetworkService, roomDB: RoomDB): ClientWebSocket {
        return ClientWebSocket(context,networkService,roomDB.usersInChatDAO(),roomDB.userDAO(),roomDB.messagesDAO())
    }
}