package com.vr.app.sh.data.api

import android.content.Context
import com.vr.app.sh.data.storage.sharedprefs.CookiePreferences
import com.vr.app.sh.domain.model.AuthorizationEntity
import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.domain.model.Question
import com.vr.app.sh.domain.model.Reg
import com.vr.app.sh.domain.model.Test
import com.vr.app.sh.domain.model.User
import com.vr.app.sh.domain.model.messages.Message
import com.vr.app.sh.domain.model.response.InfoChat
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface NetworkService {

    @POST("/login")
    suspend fun loginInServ(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("/authoriz")
    suspend fun auth(): Response<AuthorizationEntity>

    @POST("/Verification")
    suspend fun verificationUserInServer(@Query("id") user_id:Int): Response<ResponseBody>
    @Streaming
    @GET("/Photo")
    suspend fun downloadUserPhoto(@Query("id") id:Int): Response<ResponseBody>

    @Multipart
    @POST("/registration_mobile")
    suspend fun registration(@Part("user") requestBody: RequestBody,@Part user_photo: MultipartBody.Part?): Response<Reg>

    @GET("/GetAllUsers")
    suspend fun getAllUsers(): Response<List<User>>

    @POST("/add_result")
    suspend fun sendResult(@Body requestBody: RequestBody): Response<ResponseBody>

    @POST("/addquestion")
    suspend fun sendQuestions(@Body requestBody: RequestBody): Response<ResponseBody>

    @POST("/addtest")
    suspend fun sendTest(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("/Questions")
    suspend fun getAllQuestions(@Query("test_id") test_id:Int): Response<List<Question>>

    @GET("/ListTestsNames")
    suspend fun getAllTests(@Query("subject") subject:String): Response<List<Test>>

    @Streaming
    @GET("/Books")
    suspend fun downloadBook(@Query("id") id:Int): Response<ResponseBody>

    @Multipart
    @POST("/AddNewBook")
    suspend fun addBook(@Part("numclass") numClass: Int,@Query("namebook") nameBook:String,@Part fileBook: MultipartBody.Part?): Response<ResponseBody>

    @GET("/AllBooks")
    suspend fun getAllBooks(): Response<List<Book>>

    @POST("/sendMessage")
    suspend fun sendMessage(@Query("name_chat") nameChat:String,@Query("id_user_create") idUserCreate:Int,@Body requestBody: RequestBody): Response<Message>

    @GET("/getChatsInfo")
    suspend fun getInfoChat(@Query("user_id") userId:Int,@Query("id_last_message") idLastMessage:Int): Response<InfoChat>

    @GET("/SelectUsers")
    suspend fun getSelectedUsers(@Query("list") userListId:ArrayList<Int>): Response<List<User>>

    companion object {

        var networkService: NetworkService? = null

        var BASE_URL = "http://192.168.0.153:8080"

        fun getInstance(context: Context) : NetworkService {
            if (networkService == null){
                val builder = OkHttpClient.Builder()
                builder.cookieJar(CookiePreferences(context))
                builder.connectTimeout(60, TimeUnit.SECONDS)
                builder.readTimeout(60, TimeUnit.SECONDS)
                builder.writeTimeout(60, TimeUnit.SECONDS)
                val client = builder.build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                networkService = retrofit.create(NetworkService::class.java)
            }
            return networkService!!
        }
    }
}