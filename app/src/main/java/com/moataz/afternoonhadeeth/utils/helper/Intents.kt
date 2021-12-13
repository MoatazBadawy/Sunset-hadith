package com.moataz.afternoonhadeeth.utils.helper

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.moataz.afternoonhadeeth.R

object Intents {

    fun openWebPage(view: AppCompatActivity, url: String) {
        val openWebPage = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        view.startActivity(openWebPage)
    }

    fun shareAppSnackbar(view: View, text: String, sharedText: String, context: Context) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
            .setAction("مشاركة") {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.type = "text/plain"
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "$sharedText\n\nحمل تطبيق حديث الغروب - وشارك أحاديث وسيرة النبي ﷺ"
                )
                context.startActivity(Intent.createChooser(intent, ""))
            }
            .setActionTextColor(ContextCompat.getColor(context, R.color.yellow))
            .show()
    }
}