package com.example.kotlintodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_edit)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener (object: View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@EditActivity, MainActivity::class.java)
                startActivity(intent)
            }
        })
    }
}