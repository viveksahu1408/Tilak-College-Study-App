package com.example.tilakcollegestudy.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tilakcollegestudy.data.repository.AuthViewModel
import com.example.tilakcollegestudy.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (viewModel.isUserLoggedIn()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.login(email, password)
        }
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))

        }

        viewModel.loginStatus.observe(this) { result ->
            result.onSuccess {
                Toast.makeText(this, "Login successfull", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }.onFailure {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }

        }


    }
}