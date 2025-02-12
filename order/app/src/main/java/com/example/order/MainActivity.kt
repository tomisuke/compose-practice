package com.example.order

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.order.ui.theme.ForestGreen
import com.example.order.ui.theme.OrderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OrderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = ForestGreen
                ){
                    MainScreen()
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainActivityPreview() {
    MainScreen()
}