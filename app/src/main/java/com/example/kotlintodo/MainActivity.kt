package com.example.kotlintodo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlintodo.const.EditorMode
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<FloatingActionButton>(R.id.add_button)

        addButton.setOnClickListener {
            val intent = Intent(application, EditActivity::class.java)
            intent.putExtra("mode", EditorMode.CREATE)
            startActivity(intent)
        }
    }
}