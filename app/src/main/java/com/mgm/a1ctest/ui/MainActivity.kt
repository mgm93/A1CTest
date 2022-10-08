package com.mgm.a1ctest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mgm.a1ctest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}