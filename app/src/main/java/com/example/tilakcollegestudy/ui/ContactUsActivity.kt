package com.example.tilakcollegestudy.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tilakcollegestudy.R
import com.example.tilakcollegestudy.data.repository.AuthViewModel
import com.example.tilakcollegestudy.databinding.ActivityContactUsBinding
import com.example.tilakcollegestudy.databinding.ActivityMainBinding

class ContactUsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityContactUsBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}