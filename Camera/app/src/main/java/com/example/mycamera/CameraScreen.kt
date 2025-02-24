package com.example.mycamera

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CameraScreen(modifier: Modifier = Modifier) {
    var isGranted by remember { mutableStateOf(false) }
    PermissionHandler({ granted ->
        isGranted = granted
    })
    if (isGranted) {
        Box() {
            Text(text = "カメラの権限を確認", modifier = modifier)
        }
    } else {
        Text(text = "カメラのアクセス権限なし", modifier = modifier)
    }
}

@Preview
@Composable
private fun CameraScreenPreview() {
    CameraScreen()
}