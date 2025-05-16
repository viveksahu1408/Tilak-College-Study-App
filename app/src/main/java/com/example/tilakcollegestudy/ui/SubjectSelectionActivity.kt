package com.example.tilakcollegestudy.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tilakcollegestudy.R
import com.example.tilakcollegestudy.adapters.SubjectAdapter
import com.example.tilakcollegestudy.databinding.ActivityLoginBinding
import com.example.tilakcollegestudy.databinding.ActivitySubjectSelectionBinding
import com.example.tilakcollegestudy.model.SubjectModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SubjectSelectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubjectSelectionBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var selectedCourse: String
    private lateinit var selectedYear: String
    private val subjectList = mutableListOf<SubjectModel>()

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySubjectSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedCourse = intent.getStringExtra("course") ?: "Unknown"
        selectedYear = intent.getStringExtra("year") ?: "Unknown"

        recyclerView = findViewById(R.id.subjectRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadSubjectsFromFirebase()

        val adapter = SubjectAdapter(subjectList) { subject ->
            val intent = Intent(this, MaterialOptionsActivity::class.java)
            intent.putExtra("course", selectedCourse)
            intent.putExtra("year", selectedYear)
            intent.putExtra("subject", subject)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

    }

    private fun loadSubjectsFromFirebase() {
        val dbRef = FirebaseDatabase.getInstance().getReference("TilakCollegeStudyApp")
            .child(selectedCourse)
            .child(selectedYear)

//
//        if (subjectList.isEmpty()) {
//            Toast.makeText(this@SubjectSelectionActivity, "No subjects found for this course/year", Toast.LENGTH_SHORT).show()
//        }

        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                subjectList.clear()
                for (subjectSnapshot in snapshot.children) {
                    val subjectName = subjectSnapshot.key ?: continue
                    subjectList.add(SubjectModel(subjectName))
                }
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SubjectSelectionActivity, "Error fetching subjects", Toast.LENGTH_SHORT).show()
            }
        })

        binding.home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.notif.setOnClickListener {
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