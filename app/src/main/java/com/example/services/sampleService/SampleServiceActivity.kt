package com.example.services.sampleService

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.services.R
import com.example.services.databinding.ActivitySampleServiceBinding

class SampleServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySampleServiceBinding
    private lateinit var viewModel: SampleServiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_service)

        val factory = SampleServiceViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory).get(SampleServiceViewModel::class.java)

        binding.serviceViewModel = viewModel
        binding.lifecycleOwner = this
    }
}