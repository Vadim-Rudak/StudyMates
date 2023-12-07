package com.vr.app.sh.ui.door.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.util.Size
import android.util.TypedValue
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.regula.facesdk.FaceSDK
import com.regula.facesdk.enums.ImageType
import com.regula.facesdk.model.MatchFacesImage
import com.regula.facesdk.model.results.matchfaces.MatchFacesSimilarityThresholdSplit
import com.regula.facesdk.request.MatchFacesRequest
import com.vr.app.sh.R
import com.vr.app.sh.domain.UseCase.VerificationUserInServer
import com.vr.app.sh.ui.other.customAlert.loadingAlert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

class VerificationViewModel(val context: Context,private val verificationUserInServer: VerificationUserInServer):ViewModel() {

    private var imageCapture: ImageCapture?=null
    lateinit var outputDirectory: File
    var openCamera = MutableLiveData<Int>()
    var status_verification = MutableLiveData<Boolean>()
    var job: Job? = null

    lateinit var photoInCameraX:Bitmap
    val myPhotoBitmap:Bitmap = BitmapFactory.decodeFile("${Environment.getExternalStorageDirectory().path}/SchoolProg/MyProfile/myPhoto.jpg")

    init {
        openCamera.postValue(0)
    }

    fun setAnim(animationView: LottieAnimationView){
        animationView.repeatCount = LottieDrawable.INFINITE
        animationView.repeatMode = LottieDrawable.RESTART
        animationView.setAnimation("animVerification.json")
        animationView.playAnimation()
    }


    fun takePhoto(fragmentManager: FragmentManager,userId:Int){
        val imageCapture = imageCapture ?: return
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat("yy-MM-dd-HH-mm-ss-SSS", Locale.getDefault()).format(System.currentTimeMillis()) + ".jpg")


        val outputOption = ImageCapture
            .OutputFileOptions
            .Builder(photoFile)
            .build()

        imageCapture.takePicture(
            outputOption, ContextCompat.getMainExecutor(context),
            object :ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    photoInCameraX = BitmapFactory.decodeFile(photoFile.path)
                    verificationUser(fragmentManager,userId)
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("FFF","onError ${exception.message}")
                }

            }
        )
    }

    fun startCamera(viewFinder: PreviewView,lifecycleOwner: LifecycleOwner){

        val widthPreview = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300.toFloat(), context.resources.displayMetrics).toInt()
        val heightPreview = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 320.toFloat(), context.resources.displayMetrics).toInt()

        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .setTargetResolution(Size(widthPreview, heightPreview))
                .build().also {
                    it.setSurfaceProvider(
                        viewFinder.surfaceProvider
                    )

                }

            imageCapture = ImageCapture.Builder()
                .build()

            val cameraSelector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_FRONT).build()

            try{
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(lifecycleOwner,cameraSelector,preview,imageCapture)
            }catch (e:Exception){
                Log.d("FFF","error start camera")
            }
        }, ContextCompat.getMainExecutor(context))

    }

    fun verificationUser(fragmentManager: FragmentManager,userId: Int){

        val loadingView = loadingAlert(context.resources.getString(R.string.win_verification_titel),context.resources.getString(
            R.string.win_verification_info_msg))
        loadingView.show(fragmentManager,"showLoadingAlert")

        val request = MatchFacesRequest(
            listOf(
                MatchFacesImage(photoInCameraX, ImageType.PRINTED),
                MatchFacesImage(myPhotoBitmap, ImageType.PRINTED),
            )
        )

        FaceSDK.Instance().matchFaces(request) { response ->
            val split = MatchFacesSimilarityThresholdSplit(response.results, 0.75)
            if (split.matchedFaces.size==1){
                job = CoroutineScope(Dispatchers.IO).launch {

                    verificationUserInServer.execute(userId)
                    loadingView.isDone()
                    status_verification.postValue(true)
                }
            }else{
                loadingView.isDone()
                status_verification.postValue(false)
            }
        }


    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}