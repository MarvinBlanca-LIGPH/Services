package com.example.services.intentService

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.services.R
import com.example.services.databinding.ActivityIntentServiceBinding

class IntentServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntentServiceBinding
    private lateinit var viewModel: IntentServiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intent_service)

        val factory = IntentServiceViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory).get(IntentServiceViewModel::class.java)

        binding.intentServiceViewModel = viewModel
    }
}