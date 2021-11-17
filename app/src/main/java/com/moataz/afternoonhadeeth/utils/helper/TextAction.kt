package com.moataz.afternoonhadeeth.utils.helper

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.moataz.afternoonhadeeth.R

class TextAction {

    fun copyText(sharedText: String, context: Context) {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(
            "text",
            "$sharedText\n\nتم الإرسال من تطبيق حديث الغروب: أحاديث النبي ﷺ"
        )
        clipboardManager.setPrimaryClip(clipData)
    }

    fun shareTextSnackbar(view: View, text: String, sharedText: String, context: Context) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
            .setAction("مشاركة") {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.type = "text/plain"
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "$sharedText\n\nتم الإرسال من تطبيق حديث الغروب: أحاديث النبي ﷺ"
                )
                context.startActivity(Intent.createChooser(intent, ""))
            }
            .setActionTextColor(ContextCompat.getColor(context, R.color.yellow))
            .show()
    }
}


