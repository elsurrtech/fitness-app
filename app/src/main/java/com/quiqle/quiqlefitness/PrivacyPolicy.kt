package com.quiqle.quiqlefitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PrivacyPolicy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)
    }

    override fun onBackPressed() {
        val i = Intent(this,SettingsActivity::class.java)
        startActivity(i)
    }
}
