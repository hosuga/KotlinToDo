package com.example.kotlintodo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlintodo.model.EditorMode

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val editorMode = intent.getSerializableExtra("editor_mode") as EditorMode

        setTheme(R.style.AppTheme)
        setTitle(editorMode.value)
        setContentView(R.layout.activity_edit)

        val saveButton = findViewById<Button>(R.id.saveButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)

        saveButton.setOnClickListener {
            finish()
        }

        cancelButton.setOnClickListener {
            val cancelConfirmDialog = ConfirmDialog(
                    "内容は破棄されますが、キャンセルしますか？",
                    "はい",
                    {finish()},
                    "いいえ"
            )
            cancelConfirmDialog.show(supportFragmentManager, "cancel_confirm_dialog")
        }
    }
}