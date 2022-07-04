package com.neobre.architecturedemo.util

import android.app.AlertDialog
import android.content.Context

fun Context.showRetryLimitDialog(callback: () -> Unit) {
    AlertDialog.Builder(this)
        .setTitle("Warning!")
        .setMessage("Reached retry limit, please contact support!")
        .setPositiveButton("Back") { _, _ -> callback() }
        .show()
}
