package com.moataz.afternoonhadeeth.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri

class Intents {
    private val view: Activity? = null

    fun openTwitterAccountIntent() {
        val openTwitterAccount =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/sunset_hadith"))
        view!!.startActivity(openTwitterAccount)
    }

    fun openInstagramAccountIntent() {
        val openInstagramAccountIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/sunset_hadith/"))
        view!!.startActivity(openInstagramAccountIntent)
    }
}