package com.example.mycamera

import android.content.Context
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture

data class CameraState(
    val context: Context,
    val cameraProviderFuture: ListenableFuture<ProcessCameraProvider>,
    val lifecycleOwner: LifecycleOwner,
    val imageCapture: ImageCapture,
) {
    fun startCamera(ctx: Context): PreviewView {
        val previewView = PreviewView(ctx).apply {
            implementationMode = PreviewView.ImplementationMode.COMPATIBLE
        }
        val executor = ContextCompat.getMainExecutor(ctx)
        cameraProviderFuture.addListener(
            {
                val cameraProvider = cameraProviderFuture.get()
                val preview = androidx.camera.core.Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                val cameraSelector = androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture,
                    )
                } catch (e: Exception) {
                    Log.e("cameraPreview", "Use case binding failed", e)
                }
            }, executor
        )
        return previewView
    }
}