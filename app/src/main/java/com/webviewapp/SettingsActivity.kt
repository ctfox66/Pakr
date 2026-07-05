package com.webviewapp

import android.content.Context
import android.os.Bundle
import android.widget.ImageButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val PREF_UA_MODE = "ua_mode"
        const val UA_MODE_MOBILE = "mobile"
        const val UA_MODE_DESKTOP = "desktop"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupUA)
        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        val current = prefs.getString(PREF_UA_MODE, UA_MODE_MOBILE) ?: UA_MODE_MOBILE
        when (current) {
            UA_MODE_DESKTOP -> radioGroup.check(R.id.radioDesktop)
            else -> radioGroup.check(R.id.radioMobile)
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val mode = when (checkedId) {
                R.id.radioDesktop -> UA_MODE_DESKTOP
                else -> UA_MODE_MOBILE
            }
            prefs.edit().putString(PREF_UA_MODE, mode).apply()
        }

        btnBack.setOnClickListener { finish() }
    }
}
