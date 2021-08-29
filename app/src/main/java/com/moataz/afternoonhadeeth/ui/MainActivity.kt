package com.moataz.afternoonhadeeth.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.Hadith
import com.moataz.afternoonhadeeth.notification.NotificationAfternoon


class MainActivity : AppCompatActivity() {

    private lateinit var hadithButton: Button
    private lateinit var firstHadithText: TextView
    private lateinit var secondHadithText: TextView

    // toolbar icons buttons
    private lateinit var twitterButton: Button
    private lateinit var instagramButton: Button
    private lateinit var supportButton: Button
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intiViews()
        setOnClickToolbarIcons()
        setupHadithText()
        getHadith()
        setupNotification()
    }

    private fun intiViews() {
        // make the status bar white with black icons
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        // make the app support only arabic "Right to left"
        // even if the language of the device on english or others
        ViewCompat.setLayoutDirection(window.decorView, ViewCompat.LAYOUT_DIRECTION_RTL)
    }

    private fun setOnClickToolbarIcons() {
        toolbar = findViewById(R.id.toolbar)

        twitterButton = toolbar.findViewById(R.id.twitter)
        twitterButton.setOnClickListener {
            val openTwitterAccountIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/sunset_hadith"))
            startActivity(openTwitterAccountIntent)
        }

        instagramButton = toolbar.findViewById(R.id.instagram)
        instagramButton.setOnClickListener {
            val openInstagramAccountIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/sunset_hadith/"))
            startActivity(openInstagramAccountIntent)
        }

        supportButton = toolbar.findViewById(R.id.support)
        supportButton.setOnClickListener { }
    }

    private fun getHadith() {
        hadithButton = findViewById(R.id.get_hadith_button)
        hadithButton.setOnClickListener { setupHadithText() }
    }

    private fun setupHadithText() {
        // first element in the view
        firstHadithText = findViewById(R.id.first_hadith)
        firstHadithText.text = Hadith().firstHadith()
        firstHadithText.movementMethod = ScrollingMovementMethod()
        // second element in the view
        secondHadithText = findViewById(R.id.second_hadith)
        secondHadithText.text = Hadith().secondHadith()
        secondHadithText.movementMethod = ScrollingMovementMethod()
    }

    private fun setupNotification() {
        NotificationAfternoon().setupAfternoonNotification(this)
    }
}