package com.example.tilakcollegestudy.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tilakcollegestudy.R
import com.example.tilakcollegestudy.databinding.ActivityLoginBinding
import com.example.tilakcollegestudy.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
   private lateinit var binding: ActivityUpdateBinding
    private val email = " tilakcollegekatni@gmail.com"
    private val whatsappNumber = "+917999182954"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.emailButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "message/rfc822"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Tilak College App Feedback")
            startActivity(Intent.createChooser(intent, "Email भेजें..."))
        }

        binding.whatsappButton.setOnClickListener {
            val url = "https://wa.me/${whatsappNumber.replace("+", "").replace(" ", "")}"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}