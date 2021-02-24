package com.example.kotlintodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener (object: View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MainActivity, EditActivity::class.java)
                startActivity(intent)
            }
        })
    }
}