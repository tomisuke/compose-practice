package com.example.countdowntimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.Timer10
import androidx.compose.material.icons.outlined.Timer10Select
import androidx.compose.material.icons.outlined.Timer3Select
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countdowntimer.ui.theme.CountDownTimerTheme
import com.example.countdowntimer.ui.theme.Pink80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountDownTimerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    ExampleScaffold()
                }
            }
        }
    }
}

@Composable
fun ExampleScaffold(viewModel: ExampleViewModel = viewModel()) {
    val uiState = viewModel.uiState
    val iconOnClick: (Int) -> Unit = { time: Int ->
        viewModel.addTime(time)
    }
    val toggleTimer = {
        if (uiState.isRunning.value) {
            viewModel.stopTimer()
        } else {
            viewModel.startTimer(viewModel.uiState.time.value)
        }
    }
    Scaffold(topBar = { TopBar(iconOnClick = iconOnClick) },
        bottomBar = { BottomBar(iconOnClick = iconOnClick) },
        floatingActionButton = {
            FloatingActionButton(onClick = toggleTimer, content = {
                Icon(
                    imageVector = Icons.Filled.Timer,
                    contentDescription = "Timer",
                )
            })
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                val remain = uiState.timeLeft.value.toFloat()
                val total = uiState.time.value.toFloat()
                Arc(
                    color = MaterialTheme.colorScheme.primary,
                    timeLeft = remain / total,
                )
                val minute = uiState.timeLeft.value / 1000L / 60L
                val second = uiState.timeLeft.value / 1000L % 60L
                Text(
                    text = "%1$02d:%2$02d".format(minute, second),
                    fontSize = 100.sp,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        })
}

@Preview(widthDp = 300, heightDp = 500)
@Composable
private fun ExampleScaffoldPreview() {
    ExampleScaffold()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(iconOnClick: (Int) -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "CountDownTimer", maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = {/*todo*/ }) {
                Icon(
                    imageVector = Icons.Filled.Timer,
                    contentDescription = "Timer",
                )
            }
        },
        actions = {
            IconButton(onClick = { iconOnClick(3) }) {
                Icon(
                    imageVector = Icons.Outlined.Timer3Select,
                    contentDescription = "Timer3Select",
                )
            }
            IconButton(onClick = { iconOnClick(10) }) {
                Icon(
                    imageVector = Icons.Outlined.Timer10Select,
                    contentDescription = "Timer10Select",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )
}

@Preview
@Composable
private fun TopBarPreview() {
    TopBar(iconOnClick = {})
}

@Composable
fun BottomBar(
    iconOnClick: (Int) -> Unit
) {
    BottomAppBar(
        actions = {
            IconButton(onClick = { iconOnClick(60) }) {
                Icon(
                    imageVector = Icons.Default.PlusOne,
                    contentDescription = "PlusOne",
                )
            }
        },
    )
}

@Preview
@Composable
private fun BottomBarPreview() {
    BottomBar(iconOnClick = {})
}
