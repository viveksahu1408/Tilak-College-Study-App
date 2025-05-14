package com.example.tilakcollegestudy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tilakcollegestudy.R
import com.example.tilakcollegestudy.model.YearModel

class YearAdapter(
    private val yearList: List<YearModel>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<YearAdapter.YearViewHolder>() {

    class YearViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val yearText: TextView = itemView.findViewById(R.id.yearText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_year, parent, false)
        return YearViewHolder(view)
    }

    override fun onBindViewHolder(holder: YearViewHolder, position: Int) {
        val year = yearList[position]
        holder.yearText.text = year.yearName
        holder.itemView.setOnClickListener {
            onItemClick(year.yearName)
        }
    }

    override fun getItemCount(): Int = yearList.size
}
