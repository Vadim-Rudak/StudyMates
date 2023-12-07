package com.vr.app.sh.ui.other.photoPicker

import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.vr.app.sh.R
import java.text.SimpleDateFormat
import java.util.Locale

class CameraAct : AppCompatActivity() {

    private var imageCapture: ImageCapture?=null

    private lateinit var cameraProvider: ProcessCameraProvider
    private var cameraSelector:CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private lateinit var preview:Preview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        startCamera(findViewById(R.id.viewFinder1))

        val btnSelect = findViewById<ImageButton>(R.id.camera_select)
        btnSelect.setOnClickListener {
            flipCamera()
        }

        val btnTakePhoto = findViewById<ImageButton>(R.id.btn_take_photo)
        btnTakePhoto.setOnClickListener {
            takePhoto()
        }
    }

    private fun flipCamera(){
        cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA){
            CameraSelector.DEFAULT_FRONT_CAMERA
        }else{
            CameraSelector.DEFAULT_BACK_CAMERA
        }
        bindCamera()
    }

    private fun startCamera(viewFinder:PreviewView) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            cameraProvider = cameraProviderFuture.get()

            // Preview
            preview = Preview.Builder()
                .setTargetAspectRatio(AspectRatio.RATIO_16_9)
                .build()
                .also {
                    it.setSurfaceProvider(viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .setTargetAspectRatio(AspectRatio.RATIO_16_9)
                .build()

            bindCamera()
        }, ContextCompat.getMainExecutor(this))
    }

    private fun bindCamera(){
        try {
            // Unbind use cases before rebinding
            cameraProvider.unbindAll()
            // Bind use cases to camera
            cameraProvider.bindToLifecycle(this, cameraSelector, preview,imageCapture)
        } catch (exc: Exception) {
            Log.e("FFF", "Use case binding failed", exc)
        }
    }

    private fun takePhoto(){
        val imageCapture = imageCapture ?: return
        val name = SimpleDateFormat("yy-MM-dd-HH-mm-ss-SSS", Locale.getDefault()).format(System.currentTimeMillis()) + ".jpg"
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/other-img")
            }
        }

        val outputOption = ImageCapture
            .OutputFileOptions
            .Builder(contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues)
            .build()

        imageCapture.takePicture(
            outputOption, ContextCompat.getMainExecutor(this),
            object :ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    finish()
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("FFF","onError ${exception.message}")
                }
            }
        )
    }
}