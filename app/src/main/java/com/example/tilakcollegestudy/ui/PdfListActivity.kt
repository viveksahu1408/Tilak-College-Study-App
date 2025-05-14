package com.example.tilakcollegestudy.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tilakcollegestudy.R
import com.example.tilakcollegestudy.adapters.PdfAdapter
import com.example.tilakcollegestudy.databinding.ActivityPdfListBinding
import com.example.tilakcollegestudy.model.PdfModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PdfListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPdfListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PdfAdapter
    private val list = mutableListOf<PdfModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPdfListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.pdfRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val course = intent.getStringExtra("course") ?: ""
        val year = intent.getStringExtra("year") ?: ""
        val subject = intent.getStringExtra("subject") ?: ""
        val type = intent.getStringExtra("type") ?: ""

        val dbRef = FirebaseDatabase.getInstance().getReference("TilakCollegeStudyApp")
            .child(course)
            .child(year)
            .child(subject)
            .child(type)
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (data in snapshot.children) {
                    val title = data.key ?: ""
                    val url = data.getValue(String::class.java) ?: ""
                    list.add(PdfModel(title, url))
                }
                adapter = PdfAdapter(list)
                recyclerView.adapter = adapter
            }override fun onCancelled(error: DatabaseError) {
                if (list.isEmpty()) {
                    Toast.makeText(this@PdfListActivity, "No materials found", Toast.LENGTH_SHORT).show()
                }

                Toast.makeText(this@PdfListActivity, "Error loading data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}