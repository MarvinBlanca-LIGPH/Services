package com.example.services.serviceActivityComm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.services.R
import com.example.services.databinding.ActivityServiceCommBinding

class ServiceCommActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServiceCommBinding
    private lateinit var viewModel: ServiceCommViewModel
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_service_comm)

        val factory = ServiceCommViewModelFactory(this)
        viewModel = ViewModelProvider(this,factory).get(ServiceCommViewModel::class.java)

        binding.serviceCommViewModel = viewModel
        binding.lifecycleOwner = this
    }
}