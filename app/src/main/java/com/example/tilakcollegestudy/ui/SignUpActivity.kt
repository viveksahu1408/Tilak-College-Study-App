package com.example.tilakcollegestudy.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tilakcollegestudy.data.repository.AuthViewModel
import com.example.tilakcollegestudy.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginR.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

        binding.btnRegisterR.setOnClickListener {
            val email = binding.etEmailR.text.toString()
            val password = binding.etPasswordR.text.toString()
            val confirmPassword = binding.etPasswordR2.text.toString()

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.signup(email, password)
        }

        viewModel.loginStatus.observe(this) { result ->
            result.onSuccess {
                Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }.onFailure {
                Toast.makeText(this, "Signup Failed: ${it.message}", Toast.LENGTH_LONG).show()
            }
        }

    }
}