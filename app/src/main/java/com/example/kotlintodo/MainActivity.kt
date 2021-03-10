package com.example.kotlintodo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintodo.model.EditorMode
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        val list = findViewById<RecyclerView>(R.id.list)
        val addButton = findViewById<FloatingActionButton>(R.id.addButton)

        // dummyDate
        val todos = Array<String>(50){"Title$it"}

        val separateLine = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = ListAdapter(todos)
        list.setHasFixedSize(true)
        list.addItemDecoration(separateLine)

        addButton.setOnClickListener {
            val intent = Intent(application, EditActivity::class.java)
            intent.putExtra("editor_mode", EditorMode.CREATE)
            startActivity(intent)
        }
    }
}