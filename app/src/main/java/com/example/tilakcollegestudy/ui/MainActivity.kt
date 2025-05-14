package com.example.tilakcollegestudy.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tilakcollegestudy.R
import com.example.tilakcollegestudy.adapters.CourseAdapter
import com.example.tilakcollegestudy.data.repository.AuthViewModel
import com.example.tilakcollegestudy.databinding.ActivityMainBinding
import com.example.tilakcollegestudy.model.CourseModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private val viewModel: AuthViewModel by viewModels()

    private val courseList = listOf(
        CourseModel("BA"),
        CourseModel("BSc"),
        CourseModel("BCom"),
        CourseModel("MA"),
        CourseModel("MSc"),
        CourseModel("MCom"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        createNotificationChannel()
        recyclerView = findViewById(R.id.courseRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        binding.notificationIcon.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }

        //  binding.tv.text = "welcom to tilak college study app"
        binding.tv.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(this, LoginActivity::class.java))
        }
        val adapter = CourseAdapter(courseList) { selectedCourse ->
            val intent = Intent(this, YearSelectionActivity::class.java)
            intent.putExtra("course", selectedCourse)
            startActivity(intent)
        }

        recyclerView.adapter = adapter


    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "tilak_channel_id", // ✅ Use same ID as in service
                "Tilak Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Tilak College Notifications"
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}