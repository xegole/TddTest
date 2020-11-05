package com.bigthinkapps.vt.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bigthinkapps.vt.test.databinding.ActivityIntialBinding

class InitialActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(InitialViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntialBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
    }
}