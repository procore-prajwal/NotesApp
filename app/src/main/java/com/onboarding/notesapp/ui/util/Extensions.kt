package com.onboarding.notesapp.ui.util

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.onboarding.notesapp.R

fun AppCompatActivity.setStatusBarColorCompat(color: Int, lightIcons: Boolean = true) {
    // Force system bars to fit decor
    WindowCompat.setDecorFitsSystemWindows(window, true)

    // Set the status bar color even if deprecated
    window.statusBarColor = color

    // ✅ Control icon color (true = dark icons on light bg, false = light icons on dark bg)
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
}

fun AppCompatActivity.setScreenTitle(title: String) {
    val toolbar: Toolbar = findViewById(R.id.toolbar)
    setSupportActionBar(toolbar)
    supportActionBar?.title = title
}