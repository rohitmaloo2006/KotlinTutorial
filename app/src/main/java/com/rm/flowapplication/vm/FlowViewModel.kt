package com.rm.flowapplication.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FlowViewModel : ViewModel() {

    private var _liveDate = MutableLiveData("live Data")
    val liveData: LiveData<String> = _liveDate

    private var _stateFlow = MutableStateFlow("Hello state flow")
    val statFlow: StateFlow<String> = _stateFlow

    fun triggerLiveData() {
        _liveDate.value = " LiveData trigger"
    }

    fun triggerStateFlow() {
        _stateFlow.value = "State flow trigger"
    }
}