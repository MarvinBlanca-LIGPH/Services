package com.example.services.bindService

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.services.R
import com.example.services.databinding.ActivityBindServiceBinding

class BindServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBindServiceBinding
    private lateinit var viewModel: BindServiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bind_service)

        val factory = BindServiceViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory).get(BindServiceViewModel::class.java)

        binding.bindServiceViewModel = viewModel
    }
}