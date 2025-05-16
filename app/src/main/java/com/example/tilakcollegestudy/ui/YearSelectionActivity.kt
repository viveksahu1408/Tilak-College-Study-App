package com.example.tilakcollegestudy.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tilakcollegestudy.R
import com.example.tilakcollegestudy.adapters.YearAdapter
import com.example.tilakcollegestudy.databinding.ActivityMainBinding
import com.example.tilakcollegestudy.databinding.ActivityYearSelectionBinding
import com.example.tilakcollegestudy.model.YearModel

class YearSelectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYearSelectionBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var selectedCourse: String
    private val yearList = listOf(
        YearModel("Year 1"),
        YearModel("Year 2"),
        YearModel("Year 3"),
        YearModel("Year 4")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityYearSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedCourse = intent.getStringExtra("course") ?: "Unknown"

        recyclerView = findViewById(R.id.yearRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = YearAdapter(yearList) { selectedYear ->
            val intent = Intent(this, SubjectSelectionActivity::class.java)
            intent.putExtra("course", selectedCourse)
            intent.putExtra("year", selectedYear)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        binding.home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.Noti.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }
        binding.edit.setOnClickListener {
            startActivity(Intent(this,UpdateActivity::class.java))
        }
        binding.user.setOnClickListener {
            startActivity(Intent(this,ContactUsActivity::class.java))
        }
    }
}