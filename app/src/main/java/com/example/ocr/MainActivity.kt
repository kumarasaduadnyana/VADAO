package com.example.ocr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : CardView = findViewById(R.id.card_scan)
        button.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val intent = Intent(this@MainActivity, ScanData::class.java)
        startActivity(intent)
    }
}