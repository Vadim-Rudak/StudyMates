package com.vr.app.sh.di

import android.content.Context
import com.vr.app.sh.data.api.*
import com.vr.app.sh.data.api.webSocket.ClientWebSocket
import com.vr.app.sh.data.api.webSocket.WebSocketImpl
import com.vr.app.sh.data.storage.room.RoomDB
import com.vr.app.sh.data.storage.room.repo.*
import com.vr.app.sh.data.storage.sharedprefs.CookiePreferences
import com.vr.app.sh.data.storage.sharedprefs.UserPreferences
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideNetworkService(context: Context) = NetworkService.getInstance(context)
    @Provides
    fun provideInternetRepoImpl(context: Context, networkService: NetworkService) = InternetRepoImpl(context, networkService)
    @Provides
    fun providePhotoInternetRepoImpl(context: Context, networkService: NetworkService) = PhotoInternetRepoImpl(context, networkService)
    @Provides
    fun provideBookInternetRepoImpl(context: Context, networkService: NetworkService) = BookInternetRepoImpl(context, networkService)
    @Provides
    fun provideUserInternetRepoImpl(context: Context, networkService: NetworkService) = UserInternetRepoImpl(context, networkService)
    @Provides
    fun provideChatInternetRepoImpl(context: Context, networkService: NetworkService) = ChatInternetRepoImpl(context, networkService)
    @Provides
    fun provideWebSocketImpl(clientWebSocket:ClientWebSocket) = WebSocketImpl(clientWebSocket)
    @Provides
    fun provideCookiePreferences(context: Context) = CookiePreferences(context)
    @Provides
    fun provideUserPreferences(context: Context) = UserPreferences(context)
    @Provides
    fun provideRoomDB(context: Context) = RoomDB.getDatabase(context = context)
    @Provides
    fun provideBookRepoImpl(roomDB: RoomDB) = BookRepoImpl(roomDB.bookDAO())
    @Provides
    fun provideLessonsRepoImpl(roomDB: RoomDB) = LessonsRepoImpl(roomDB.lessonsDAO())
    @Provides
    fun provideQuestionsRepoImpl(roomDB: RoomDB) = QuestionsRepoImpl(roomDB.questionsDAO())
    @Provides
    fun provideTestRepoImpl(roomDB: RoomDB) = TestRepoImpl(roomDB.testDAO())
    @Provides
    fun provideUserRepoImpl(roomDB: RoomDB) = UserRepoImpl(roomDB.userDAO())
    @Provides
    fun provideUsersInChatRepoImpl(roomDB: RoomDB) = UsersInChatRepoImpl(roomDB.usersInChatDAO())
    @Provides
    fun provideMessagesRepoImpl(roomDB: RoomDB) = MessagesRepoImpl(roomDB.messagesDAO())
    @Provides
    fun provideFavoriteRepoImpl(roomDB: RoomDB) = FavoriteRepoImpl(roomDB.favoriteUserDAO())
    @Provides
    fun provideClientWebSocket(context: Context,networkService: NetworkService, roomDB: RoomDB): ClientWebSocket {
        return ClientWebSocket(context,networkService,roomDB.usersInChatDAO(),roomDB.userDAO(),roomDB.messagesDAO())
    }
}