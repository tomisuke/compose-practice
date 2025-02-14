package com.example.countdowntimer

class ExampleViewModel {
    var uiState = ExampleUiState()
        private set//セッターをプライベート型にする。外部から参照することはできるが、値を変更することはできない
    var timer: MyCountDownTimer? = null

    fun startTimer(millisInFuture: Long, countDownInterval: Long) {
        uiState.time.value = millisInFuture
        uiState.isRunning.value = true
        timer = MyCountDownTimer(
            millisInFuture = millisInFuture,
            countDownInterval = 100L,
            changed = { millisUntilFinished ->
                uiState.time.value = millisInFuture
                uiState.timeLeft.value = millisUntilFinished
                uiState.isRunning.value = true
            },
            finished = {
                uiState.time.value = millisInFuture
                uiState.timeLeft.value = 0
                uiState.isRunning.value = false
            }
        )
        timer?.start()
    }

    fun stopTimer() {
        timer?.cancel()
        uiState.time.value = 3 * 60 * 1000
        uiState.timeLeft.value = 3 * 60 * 1000
        uiState.isRunning.value = false
    }

    fun addTime(second: Int) {
        if (!uiState.isRunning.value) {
            val newTime = uiState.time.value + second * 1000L
            uiState.time.value = newTime
            uiState.timeLeft.value = newTime
            uiState.isRunning.value = false
        }
    }
}