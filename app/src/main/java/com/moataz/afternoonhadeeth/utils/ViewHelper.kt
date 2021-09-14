package com.moataz.afternoonhadeeth.utils

import android.view.View
import android.view.Window
import androidx.core.view.ViewCompat

object ViewHelper {

    fun intiViews(window: Window) {
        // make the status bar white with black icons
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        // make the app support only arabic "Right to left"
        // even if the language of the device on english or others
        ViewCompat.setLayoutDirection(window.decorView, ViewCompat.LAYOUT_DIRECTION_RTL)
    }
}