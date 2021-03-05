package com.example.kotlintodo

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ConfirmDialog(private val message: String,
                    private val confirmLabel: String,
                    private val confirm: () -> Unit,
                    private val cancelLabel: String)
    : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())

        builder.setMessage(message)
        builder.setPositiveButton(confirmLabel) { _, _ ->
            confirm()
        }
        builder.setNegativeButton(cancelLabel) { dialog, _ ->
            dialog.cancel()
        }

        return builder.create()
    }
}