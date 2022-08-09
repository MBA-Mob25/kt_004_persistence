package com.example.kt_004_shared_preferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_treatment.*

class TreatmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatment)
        loadPrefs()
    }

    private fun loadPrefs() {
        val prefs = this.getSharedPreferences("storageTreatment", Context.MODE_PRIVATE)
        val name = prefs.getString("name", "")
        val treatment = prefs.getString("treatment", "")

        if (treatment == "Sem tratamento") {
            showTreatment.text = name;
        } else {
            val fullText = "$treatment $name";
            showTreatment.text = fullText
        }
    }
}