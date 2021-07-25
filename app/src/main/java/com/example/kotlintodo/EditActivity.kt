package com.example.kotlintodo

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlintodo.model.EditorMode
import com.example.kotlintodo.realm.ToDoAccessor

class EditActivity : AppCompatActivity() {
    val ERROR_MESSAGES = mapOf(
        "HEADER" to "ERROR!",
        "EMPTY_TITLE" to "件名を入力して下さい。",
        "TOO_LONG_TITLE" to "20文字以内で入力して下さい。"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val editorMode = intent.getSerializableExtra("editorMode") as EditorMode
        val todoId = intent.getSerializableExtra("todoId") as Long?

        setTheme(R.style.AppTheme)
        setTitle(editorMode.value)
        setContentView(R.layout.activity_edit)

        val saveButton = findViewById<Button>(R.id.saveButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val editTitleForm = findViewById<EditText>(R.id.editTitleForm)

        if (editorMode == EditorMode.EDIT && todoId != null) {
            editTitleForm.setText(ToDoAccessor.find(todoId)?.title)
        }

        saveButton.setOnClickListener {
            val title = editTitleForm.text.toString()
            if (validate((title))) {
                if (editorMode == EditorMode.CREATE) {
                    ToDoAccessor.create(title)
                } else {
                    if (todoId != null) {
                        ToDoAccessor.update(todoId, title)
                    }
                }
                finish()
            }

        }

        cancelButton.setOnClickListener {
            ConfirmDialog(
                    "内容は破棄されますが、キャンセルしますか？",
                    "はい",
                    {finish()},
                    "いいえ"
            ).show(supportFragmentManager, "cancel_confirm_dialog")
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