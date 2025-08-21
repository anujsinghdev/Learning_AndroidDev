package com.example.cameraxapp

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import android.os.Build
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun CameraX() {

    val context = LocalContext.current

    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    val previewView = remember {
        PreviewView(context)
    }

    val preview = androidx.camera.core.Preview.Builder().build()

    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    val imageCapture = remember {
        ImageCapture.Builder().build()
    }

    LaunchedEffect(key1 = Unit) {
        val cameraProvider = context.getCameraProvider()

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageCapture

            )

            preview.setSurfaceProvider(previewView.surfaceProvider)
        } catch (e: Exception) {
            Toast.makeText(
                context, "Failed to launch camera",
                Toast.LENGTH_LONG
            ).show()
        }
    }




    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                previewView
            }
        )

        // Professional Camera Button Design
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp),
            contentAlignment = Alignment.Center
        ) {
            // Outer ring (larger)
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        Color.White.copy(alpha = 0.3f),
                        CircleShape
                    )
            )

            // Middle ring
            Box(
                modifier = Modifier
                    .size(84.dp)
                    .background(
                        Color.Gray.copy(alpha = 0.8f),
                        CircleShape
                    )
            )

            // Inner button
            FloatingActionButton(
                onClick = {
                    capturePhoto(
                        imageCapture = imageCapture,
                        context = context
                    )
                },
                modifier = Modifier.size(68.dp),
                containerColor = Color(0xFF616161), // Medium grey
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 10.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Camera,
                    contentDescription = "Take Photo",
                    modifier = Modifier.size(28.dp),
                    tint = Color.White
                )
            }
        }
    }



}


private suspend fun Context.getCameraProvider():
        ProcessCameraProvider = suspendCoroutine {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

    cameraProviderFuture.addListener(
        {
            it.resume(cameraProviderFuture.get())
        },
        ContextCompat.getMainExecutor(this)
    )
}

private fun capturePhoto(imageCapture: ImageCapture, context: Context) {
    val name = "CameraX-${System.currentTimeMillis()}.jpg"

    val contentValue = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
    }


    // Create output options object which contains file + metadata
    val outputOptions = ImageCapture.OutputFileOptions
        .Builder(
            context.contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValue
        )
        .build()

    // Set up image capture listener, which is triggered after photo has
    // been taken
    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onError(exc: ImageCaptureException) {
                Toast.makeText(
                    context,
                    "Photo capture failed: ${exc.message}",
                    Toast.LENGTH_LONG
                ).show()
                Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
            }

            override fun
                    onImageSaved(output: ImageCapture.OutputFileResults) {
                val msg = "Photo capture succeeded: ${output.savedUri}"
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                Log.d(TAG, msg)
            }
        }
    )
}