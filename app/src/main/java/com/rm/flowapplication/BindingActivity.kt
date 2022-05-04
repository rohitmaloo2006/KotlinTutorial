package com.rm.flowapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rm.flowapplication.databinding.DatabindingBinding
import com.rm.flowapplication.vm.BindingViewModel

class BindingActivity : AppCompatActivity() {

    private lateinit var binding: DatabindingBinding
    lateinit var viewModel: BindingViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* val splashViewModel: SplashViewModel by viewModels()
         installSplashScreen().apply {
             this.setKeepOnScreenCondition {
                 splashViewModel.loading.value
             }
         }*/

        binding = DataBindingUtil.setContentView(this, R.layout.databinding)
        viewModel = ViewModelProvider(this)[BindingViewModel::class.java]

        binding.bindingViewModel = viewModel
        binding.lifecycleOwner = this


    }
}