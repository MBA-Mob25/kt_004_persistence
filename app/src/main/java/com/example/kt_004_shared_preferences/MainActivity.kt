package com.example.kt_004_shared_preferences

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSave.setOnClickListener(View.OnClickListener { onSave() });
        btnShow.setOnClickListener(View.OnClickListener { onShow() })
    }

    private fun onSave() {
        val prefs = this.getSharedPreferences("storageTreatment", Context.MODE_PRIVATE)
        val editor = prefs.edit()

        editor.putString("name", inputName.text.toString())
        editor.putString("treatment", listTreatment.selectedItem.toString())
        editor.apply()

        Toast.makeText(this, "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
    }

    private fun onShow() {
        val intent = Intent(this, TreatmentActivity::class.java)
        startActivity(intent)
    }

}