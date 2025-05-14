package com.example.tilakcollegestudy.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tilakcollegestudy.R
import com.example.tilakcollegestudy.adapters.NotificationAdapter

class NotificationActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificationAdapter
    private val notifications = mutableListOf<String>() // Placeholder list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        recyclerView = findViewById(R.id.recyclerViewNotifications)
        adapter = NotificationAdapter(notifications)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        loadNotifications()
    }

    private fun loadNotifications() {
        // Static sample data (later use Firestore)
        notifications.add("📢 Welcome to Tilak College Study App!")
        notifications.add("🆕 New Notes Uploaded for BCA 2nd Year")
        notifications.add("📅 Check Out the Updated Exam Syllabus")
        adapter.notifyDataSetChanged()
    }
}
