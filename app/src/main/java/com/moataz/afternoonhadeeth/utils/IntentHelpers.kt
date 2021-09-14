package com.moataz.afternoonhadeeth.utils

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity

object IntentHelpers {

    fun openTwitterAccountIntent(view: AppCompatActivity) {
        val openTwitterAccount =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/sunset_hadith"))
        view.startActivity(openTwitterAccount)
    }

    fun openInstagramAccountIntent(view: AppCompatActivity) {
        val openInstagramAccountIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/sunset_hadith/"))
        view.startActivity(openInstagramAccountIntent)
    }
}