package com.example.services.callbackService

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.services.R
import com.example.services.databinding.ActivityCallbackServiceBinding

class CallbackServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCallbackServiceBinding
    private lateinit var viewModel: CallbackViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_callback_service)

        val factory = CallbackViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory).get(CallbackViewModel::class.java)

        binding.callbackViewModel = viewModel
        binding.lifecycleOwner = this
    }
}