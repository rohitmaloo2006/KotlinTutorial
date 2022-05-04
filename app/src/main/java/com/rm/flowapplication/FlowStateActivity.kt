package com.rm.flowapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.rm.flowapplication.databinding.ActivityFlowStateBinding
import com.rm.flowapplication.model.Flow
import com.rm.flowapplication.vm.FlowViewModel
import kotlinx.coroutines.flow.collectLatest

class FlowStateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlowStateBinding

    private val flowViewMode: FlowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_flow_state)
        binding.btLivedata.setOnClickListener {
            flowViewMode.triggerLiveData()
        }

        binding.btStateflow.setOnClickListener {
            flowViewMode.triggerStateFlow()
            triggerStateFlow()
        }
        subscribeToObservables()

    }

    private fun subscribeToObservables() {
        flowViewMode.liveData.observe(this) {
            binding.flow = Flow(live = it)
        }

    }

    private fun triggerStateFlow() {
        lifecycleScope.launchWhenStarted {
            flowViewMode.statFlow.collectLatest {
                binding.flow = Flow(stflow = it)
            }
        }
    }
}