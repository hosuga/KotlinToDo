package com.example.kotlintodo

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlintodo.model.EditorMode

class EditActivity : AppCompatActivity() {
    val ERROR_MESSAGES = mapOf(
        "HEADER" to "ERROR!",
        "EMPTY_TITLE" to "件名を入力して下さい。",
        "TOO_LONG_TITLE" to "20文字以内で入力して下さい。"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val editorMode = intent.getSerializableExtra("editor_mode") as EditorMode

        setTheme(R.style.AppTheme)
        setTitle(editorMode.value)
        setContentView(R.layout.activity_edit)

        val saveButton = findViewById<Button>(R.id.saveButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val editTitleForm = findViewById<EditText>(R.id.editTitleForm)

        saveButton.setOnClickListener {
            val title = editTitleForm.text.toString()
            Log.d("title", title)
            if (validate((title))) {
                // TODO: DB更新
                finish()
            }

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

    private fun validate(title: String): Boolean {
        if (isEmpty(title.trim())) {
            AlertDialog.Builder(this)
                .setTitle(ERROR_MESSAGES["HEADER"])
                .setMessage(ERROR_MESSAGES["EMPTY_TITLE"])
                .setPositiveButton("OK"){ _, _ -> }
                .show()
            return false
        }
        if (title.length > 20) {
            AlertDialog.Builder(this)
                .setTitle(ERROR_MESSAGES["HEADER"])
                .setMessage(ERROR_MESSAGES["TOO_LONG_TITLE"])
                .setPositiveButton("OK"){ _, _ -> }
                .show()
            return false
        }
        return true
    }
}