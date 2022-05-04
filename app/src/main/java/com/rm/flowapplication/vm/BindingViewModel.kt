package com.rm.flowapplication.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BindingViewModel : ViewModel() {

    private var _counterLiveData = MutableLiveData<String>()
    var counterLiveData = _counterLiveData

    fun updateCounter(): Unit {
        _counterLiveData.value = "counter"
    }
}