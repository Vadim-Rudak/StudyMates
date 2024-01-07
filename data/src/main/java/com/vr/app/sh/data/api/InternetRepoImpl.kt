package com.vr.app.sh.data.api

import android.content.Context
import com.google.gson.Gson
import com.vr.app.sh.domain.model.AuthorizationEntity
import com.vr.app.sh.domain.model.Question
import com.vr.app.sh.domain.model.Reg
import com.vr.app.sh.domain.model.ResultTest
import com.vr.app.sh.domain.model.Test
import com.vr.app.sh.domain.model.User
import com.vr.app.sh.domain.model.response.ListResponse
import com.vr.app.sh.domain.model.response.SendResponse
import com.vr.app.sh.domain.repository.internet.DoorInSystemRepo
import com.vr.app.sh.domain.repository.internet.QuestionsInternetRepo
import com.vr.app.sh.domain.repository.internet.ResultInternetRepo
import com.vr.app.sh.domain.repository.internet.TestsInternetRepo
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File
import java.net.URLConnection

class InternetRepoImpl(val context: Context,private val networkService: NetworkService):
    DoorInSystemRepo, TestsInternetRepo, QuestionsInternetRepo, ResultInternetRepo {

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

    override suspend fun getListTestsInSub(nameSubject: String): ListResponse<Test> {
        networkService.getAllTests(nameSubject).apply {
            return if (isSuccessful){
                ListResponse(true,body())
            }else{
                ListResponse()
            }
        }
    }

    override suspend fun sendTest(nameSubject: String, numClass: Int, nameTest: String, size: Int): SendResponse {
        val request = networkService.sendTest(getJSONTest(nameSubject,numClass,nameTest,size))
        return SendResponse(success = request.isSuccessful)
    }

    fun getJSONTest(subject:String, numClass: Int?, nameTest:String, numQuestions:Int): RequestBody {
        val jsonObject = JSONObject().apply {
            put("subject", subject)
            put("numclass", numClass)
            put("nametest", nameTest)
            put("numquestions", numQuestions)
        }

        val jsonObjectString = jsonObject.toString()
        return jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
    }

    override suspend fun getTestQuestions(numClass: Int): ListResponse<Question> {
        networkService.getAllQuestions(numClass).apply {
            return if (isSuccessful){
                ListResponse(true,body())
            }else{
                ListResponse()
            }
        }
    }

    override suspend fun sendQuestions(jsonQuestions: String): SendResponse {
        val request = networkService.sendQuestions(jsonQuestions.toRequestBody("application/json".toMediaTypeOrNull()))
        return SendResponse(success = request.isSuccessful)
    }

    override suspend fun sendResult(result: ResultTest): SendResponse {
        val response = networkService.sendResult(JSONResult(result))
        return SendResponse(success = response.isSuccessful)
    }

    fun JSONResult(resultTest: ResultTest): RequestBody {
        val jsonObject = JSONObject()
        jsonObject.put("idtest", resultTest.test_id)
        //jsonObject.put("username", getUserBD.execute().user_name)
        jsonObject.put("score", resultTest.all_result)
        jsonObject.put("num_correct_otv", resultTest.num_correct_answer)
        jsonObject.put("num_error_otv", resultTest.num_wrong_answer)
        jsonObject.put("num_no_otv", resultTest.num_not_answer)

        val jsonObjectString = jsonObject.toString()
        return jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
    }
}