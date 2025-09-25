package com.example.onlineshop.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppDialog(
    showDialog: Boolean,
    title: String = "Delete Item",
    message: String = "Are you sure you want to delete this item?",
    confirmButtonText: String = "Delete",
    cancelButtonText: String = "Cancel",
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(title) },
            text = { Text(message) },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(confirmButtonText, color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { onCancel() }) {
                    Text(cancelButtonText)
                }
            }
        )
    }
}