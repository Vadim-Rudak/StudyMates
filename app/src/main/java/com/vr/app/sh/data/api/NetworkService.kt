package com.vr.app.sh.data.api

import com.vr.app.sh.domain.model.*
import com.vr.app.sh.ui.door.cookie.MyCookieJar
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.net.URI.create
import java.util.concurrent.TimeUnit

interface NetworkService {

    @POST("/login")
    suspend fun loginInServ(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("/authoriz")
    suspend fun auth(): Response<Auth>

    @POST("/reg_mob")
    suspend fun registration(@Body requestBody: RequestBody): Response<Reg>

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
    fun downloadFileWithFixedUrl(@Query("id") id:Int): Call<ResponseBody>

    @GET("/AllBooks")
    suspend fun getAllBooks(): Response<List<Book>>

    companion object {

        var networkService: NetworkService? = null

        var BASE_URL = "http://192.168.52.1:8080"

        fun getInstance() : NetworkService {
            if (networkService == null){
                val builder = OkHttpClient.Builder()
                builder.cookieJar(MyCookieJar())
                builder.connectTimeout(60, TimeUnit.SECONDS)
                builder.readTimeout(60, TimeUnit.SECONDS)
                builder.writeTimeout(60, TimeUnit.SECONDS)
                var client = builder.build()

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