package com.rm.flowapplication.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class MainViewModel : ViewModel() {

lateinit var result: ArrayList<Int>
    val countDownFlow = flow<Int> {
        val startValue = 10
        var currentValue = startValue
        emit(startValue)
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }
}