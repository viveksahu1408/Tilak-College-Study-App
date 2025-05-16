package com.example.tilakcollegestudy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tilakcollegestudy.R
import com.example.tilakcollegestudy.model.CourseModel

class CourseAdapter(
    private val courseList: List<CourseModel>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseText: TextView = itemView.findViewById(R.id.courseText)
        val courseImage: ImageView = itemView.findViewById(R.id.courseImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courseList[position]
        holder.courseText.text = course.courseName
        holder.courseImage.setImageResource(course.imageResId)

        holder.itemView.setOnClickListener {
            onItemClick(course.courseName)
        }
    }

    override fun getItemCount(): Int = courseList.size
}
