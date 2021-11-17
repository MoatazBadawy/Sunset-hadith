package com.moataz.afternoonhadeeth.utils.helper

import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.view.ViewCompat

object Views {

    fun intiViews(window: Window) {
        // make the status bar white with black icons
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        // make the app support only arabic "Right to left"
        // even if the language of the device on english or others
        ViewCompat.setLayoutDirection(window.decorView, ViewCompat.LAYOUT_DIRECTION_RTL)
    }

    fun hideStatusBar(window: Window) {
        // Hide status bar
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}