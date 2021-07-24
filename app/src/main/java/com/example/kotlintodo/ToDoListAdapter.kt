package com.example.kotlintodo

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintodo.db.ToDo
import com.example.kotlintodo.model.EditorMode
import com.example.kotlintodo.realm.ToDoAccessor
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class ToDoListAdapter(application: Application, context: Context, supportFragmentManager: FragmentManager, data: OrderedRealmCollection<ToDo>) :
    RealmRecyclerViewAdapter<ToDo, ToDoListAdapter.ViewHolder>(data, true){

    init {
        setHasStableIds(true)
    }

    val context = context
    val application = application
    val supportFragmentManager = supportFragmentManager

    class ViewHolder(val cell: View): RecyclerView.ViewHolder(cell) {
        val title = cell.findViewById<TextView>(R.id.title)
        val checkBox = cell.findViewById<CheckBox>(R.id.checkBox)
        val editButton = cell.findViewById<Button>(R.id.editButton)
        val deleteButton = cell.findViewById<Button>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo: ToDo? = getItem(position)

        holder.title.text = todo?.title

        holder.checkBox.setOnCheckedChangeListener(null)

        holder.checkBox.setOnClickListener {
            val check = holder.checkBox.isChecked

            if (check && todo != null) {
                ToDoAccessor.delete(todo.id)
            }
        }

        holder.editButton.setOnClickListener {
            val intent = Intent(application, EditActivity::class.java)
            intent.putExtra("editor_mode", EditorMode.EDIT)
            startActivity(this.context, intent, null)
        }

        holder.deleteButton.setOnClickListener {
            val deleteConfirmDialog = ConfirmDialog(
                "削除しますか？",
                "はい",
                {
                    if (todo != null) {
                        ToDoAccessor.delete(todo.id)
                    }
                },
                "いいえ"
            )
            deleteConfirmDialog.show(this.supportFragmentManager, "cancel_confirm_dialog")

        }

    }

    override fun getItemId(position: Int): Long {
        return getItem(position)?.id ?:-1
    }
}
