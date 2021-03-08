package com.example.kotlintodo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val customList: Array<String>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>(){

    class ListViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ListViewHolder(item)
    }

    override fun getItemCount(): Int {
        return customList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.title.setText(customList[position])
    }
}