package com.example.kotlintodo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val title = intent.getStringExtra("title")

        setTheme(R.style.AppTheme)
        setTitle(title)
        setContentView(R.layout.activity_edit)

        val saveButton = findViewById<Button>(R.id.save_button)
        val cancelButton = findViewById<Button>(R.id.cancel_button)

        saveButton.setOnClickListener {
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}