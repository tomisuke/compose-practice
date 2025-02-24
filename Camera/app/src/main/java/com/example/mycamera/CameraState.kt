package com.example.mycamera

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture

data class CameraState (
    val context: Context,
    val cameraProviderFuture: ListenableFuture<ProcessCameraProvider>,
    val lifecycleOwner: LifecycleOwner,
    val imageCapture: ImageCapture,
){
    fun startCamera(ctx: Context): PreviewView{
        val previewView = PreviewView(ctx).apply{
            implementationMode = Preweiw
        }
    }
}