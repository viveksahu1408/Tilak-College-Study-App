package com.example.tilakcollegestudy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tilakcollegestudy.R
import com.example.tilakcollegestudy.model.SubjectModel

class SubjectAdapter(
    private val subjectList: List<SubjectModel>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {


    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val subjectText: TextView = itemView.findViewById(R.id.subjectText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subject, parent, false)
        return SubjectViewHolder(view)

    }

    override fun getItemCount(): Int = subjectList.size

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject = subjectList[position]
        holder.subjectText.text = subject.subjectName
        holder.itemView.setOnClickListener {
            onItemClick(subject.subjectName)
        }
    }
}