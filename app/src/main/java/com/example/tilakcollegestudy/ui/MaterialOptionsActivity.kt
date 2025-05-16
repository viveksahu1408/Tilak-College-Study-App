package com.example.tilakcollegestudy.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tilakcollegestudy.R
import com.example.tilakcollegestudy.databinding.ActivityMaterialOptionsBinding
import com.example.tilakcollegestudy.databinding.ActivitySubjectSelectionBinding

class MaterialOptionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMaterialOptionsBinding
    private lateinit var course: String
    private lateinit var year: String
    private lateinit var subject: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMaterialOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        course = intent.getStringExtra("course") ?: ""
        year = intent.getStringExtra("year") ?: ""
        subject = intent.getStringExtra("subject") ?: ""

        binding.tvTitle.text = "$course → $year → $subject"

        binding.btnNotes.setOnClickListener {
            openMaterialActivity("Notes")
        }

       binding.btnSyllabus.setOnClickListener {
            openMaterialActivity("Syllabus")
        }

        binding.btnPYQ.setOnClickListener {
            openMaterialActivity("Previous Year Questions")
        }

        binding.home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.noti.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }
        binding.edit.setOnClickListener {
            startActivity(Intent(this,UpdateActivity::class.java))
        }
        binding.user.setOnClickListener {
            startActivity(Intent(this,ContactUsActivity::class.java))
        }
    }
    private fun openMaterialActivity(type: String) {
        val intent = Intent(this, PdfListActivity::class.java)
        intent.putExtra("course", course)
        intent.putExtra("year", year)
        intent.putExtra("subject", subject)
        intent.putExtra("type", type)
        startActivity(intent)
    }
}