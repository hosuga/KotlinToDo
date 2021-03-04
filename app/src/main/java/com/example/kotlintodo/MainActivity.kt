package com.example.kotlintodo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlintodo.const.Mode
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        val createButton = findViewById<FloatingActionButton>(R.id.create_button)

        createButton.setOnClickListener {
            val intent = Intent(application, EditActivity::class.java)
            intent.putExtra("mode", Mode.CREATE)
            startActivity(intent)
        }
    }
}