package com.example.services.foregroundService

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.services.R
import com.example.services.databinding.ActivityForegroundServiceBinding

class ForegroundServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForegroundServiceBinding
    private lateinit var viewModel: ForegroundViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_foreground_service)

        val factory = ForegroundViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory).get(ForegroundViewModel::class.java)

        binding.foregroundViewModel = viewModel
        binding.lifecycleOwner = this
    }
}