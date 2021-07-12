package com.example.kotlintodo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintodo.db.ToDo
import com.example.kotlintodo.realm.ToDoAccessor
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class ToDoListAdapter(data: OrderedRealmCollection<ToDo>) :
    RealmRecyclerViewAdapter<ToDo, ToDoListAdapter.ViewHolder>(data, true){

    init {
        setHasStableIds(true)
    }

    class ViewHolder(val cell: View): RecyclerView.ViewHolder(cell) {
        val title = cell.findViewById<TextView>(R.id.title)
        val checkBox = cell.findViewById<CheckBox>(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo: ToDo? = getItem(position)

        holder.title.text = todo?.title

        holder.checkBox.setOnClickListener {
            val check = holder.checkBox.isChecked

            if (check && todo != null) {
                ToDoAccessor.delete(todo.id)
            }
        }

    }

    override fun getItemId(position: Int): Long {
        return getItem(position)?.id ?:-1
    }
}
