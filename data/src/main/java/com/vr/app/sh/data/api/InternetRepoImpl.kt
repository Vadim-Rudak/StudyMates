package com.vr.app.sh.data.api

import android.content.Context
import com.google.gson.Gson
import com.vr.app.sh.domain.model.AuthorizationEntity
import com.vr.app.sh.domain.model.Book
import com.vr.app.sh.domain.model.Question
import com.vr.app.sh.domain.model.Reg
import com.vr.app.sh.domain.model.Test
import com.vr.app.sh.domain.model.User
import com.vr.app.sh.domain.repository.internet.BookInternetRepo
import com.vr.app.sh.domain.repository.internet.DoorInSystemRepo
import com.vr.app.sh.domain.repository.internet.PhotoInternetRepo
import com.vr.app.sh.domain.repository.internet.QuestionsInternetRepo
import com.vr.app.sh.domain.repository.internet.ResultInternetRepo
import com.vr.app.sh.domain.repository.internet.TestsInternetRepo
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.net.URLConnection

class InternetRepoImpl(val context: Context,private val networkService: NetworkService):
    DoorInSystemRepo, BookInternetRepo, TestsInternetRepo, QuestionsInternetRepo, ResultInternetRepo {

    override suspend fun authorization(login: String, password: String): AuthorizationEntity {
        if(NetworkService.getInstance(context = context).loginInServ(authUserInfo(login,password )).isSuccessful){
            return networkService.auth().body()!!
        }else{
            return AuthorizationEntity(false,"Ошибка подключения к серверу",null)
        }
    }

    private fun authUserInfo(userName: String, password: String): RequestBody {
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("username", userName)
            .addFormDataPart("password", password)
            .build()
    }

    override suspend fun registration(user: User, fileUserPhoto: File): Reg {

        val filePhoto:MultipartBody.Part? = prepareFilePart("user_photo",fileUserPhoto)
        user.photo.name = "myPhoto.${fileUserPhoto.extension}"
        return networkService.registration(userInJSON(user),filePhoto).body()!!
    }

    private fun userInJSON(user: User): RequestBody {
        return Gson().toJson(user).toRequestBody("multipart/form-data".toMediaTypeOrNull())
    }

    private fun prepareFilePart(partName: String, file: File): MultipartBody.Part? {
        if (!file.exists()){
            val requestFile: RequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "")
            // MultipartBody.Part is used to send also the actual file name
            return MultipartBody.Part.createFormData(partName, file.name, requestFile)
        }else{
            val mimeType2: String = URLConnection.guessContentTypeFromName(file.name)
            return if (mimeType2 != null) {
                val requestFile: RequestBody = RequestBody.create(mimeType2.toMediaTypeOrNull(), file)
                // MultipartBody.Part is used to send also the actual file name
                MultipartBody.Part.createFormData(partName, file.name, requestFile)
            } else {
                null
            }
        }
    }

    override suspend fun getAllBookList(): Response<List<Book>> {
        return networkService.getAllBooks()
    }

    override fun getBookFile(id_book: Int): Call<ResponseBody> {
        return networkService.downloadFileWithFixedUrl(id_book)
    }

    override suspend fun getListTestsInSub(name_subject: String): Response<List<Test>> {
        return networkService.getAllTests(name_subject)
    }

    override suspend fun sendTest(requestBody: RequestBody): Response<ResponseBody> {
        return networkService.sendTest(requestBody)
    }

    override suspend fun getTestQuestions(num_class: Int): Response<List<Question>> {
        return networkService.getAllQuestions(num_class)
    }

    override suspend fun sendQuestions(requestBody: RequestBody): Response<ResponseBody> {
        return networkService.sendQuestions(requestBody)
    }

    override suspend fun sendResult(requestBody: RequestBody): Response<ResponseBody> {
        return networkService.sendResult(requestBody)
    }
}